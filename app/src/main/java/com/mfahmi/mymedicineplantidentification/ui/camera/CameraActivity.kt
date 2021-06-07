package com.mfahmi.mymedicineplantidentification.ui.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.ActivityCameraBinding
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.models.PlantFirebaseModel
import com.mfahmi.mymedicineplantidentification.ml.MedicineLeafModel
import com.mfahmi.mymedicineplantidentification.ui.adapter.ProductsAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.model.Model
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
class CameraActivity : AppCompatActivity() {
    private val binding: ActivityCameraBinding by viewBinding()
    private val viewModel: CameraViewModel by viewModel()
    private var bitmapResult: Bitmap? = null
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            run {
                bitmap?.let {
                    binding.imgCameraCapture.setImageBitmap(bitmap)
                    bitmapResult = bitmap
                    binding.btnAnalyzePhoto.setVisibility(true)
                }
            }
        }
    private val leafModel: MedicineLeafModel by lazy {
        val options = if (CompatibilityList().isDelegateSupportedOnThisDevice) {
            Model.Options.Builder().setDevice(Model.Device.GPU).build()
        } else {
            Model.Options.Builder().setNumThreads(4).build()
        }
        MedicineLeafModel.newInstance(this, options)
    }
    private val getDate: String by lazy {
        val dateFormatter = DateTimeFormatter.ofPattern("h:mm a | dd - MMM - yyyy")
        LocalDateTime.now().format(dateFormatter)
    }
    private val fDatabase: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnCloseCameraAct.setOnClickListener { finish() }

        checkPermissionCam()
        binding.btnAddPhoto.setOnClickListener {
            takePicture.launch(null)
        }
        binding.btnAnalyzePhoto.setOnClickListener {
            setResultAnalyze()
        }
        binding.btnSaveBookmark.setOnClickListener {
            viewModel.insertPlantData(
                PlantDomain(
                    binding.tvTitlePlants.text.toString(),
                    binding.tvDescriptionPlant.text.toString(),
                    getDate,
                    bitmapResult as Bitmap
                )
            )
            FancyToast.makeText(
                this, getString(R.string.msg_save_bookmark), FancyToast.LENGTH_SHORT,
                FancyToast.SUCCESS, false
            ).show()
            binding.btnSaveBookmark.setVisibility(false)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQ_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.btnAddPhoto.isEnabled = true
        }
    }

    private fun checkPermissionCam() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQ_CODE
            )
        } else {
            binding.btnAddPhoto.isEnabled = true
        }
    }

    private fun populateProducts() {
        with(binding.rvProducts) {
            layoutManager =
                LinearLayoutManager(this@CameraActivity, LinearLayoutManager.HORIZONTAL, false)
            viewModel.plantsDummy.observe(this@CameraActivity) {
                adapter = ProductsAdapter(it)
            }
        }

    }

    private fun setResultAnalyze() {
        bitmapResult?.let {
            val outputs = leafModel.process(TensorImage.fromBitmap(bitmapResult))
            val probability =
                outputs.probabilityAsCategoryList.apply { sortByDescending { it.score } }
                    .take(1)
            probability.forEach {
                with(binding) {
                    pgbCamera.setVisibility(true)
                    grpContentGroup.setVisibility(true)
                    tvTitlePlants.text = it.label
                    tvPredictionValue.text = getString(R.string.prediction_format, it.score)
                    populateProducts()
                    getFirebaseData(it.label)
                }
            }
        }
    }

    private fun getFirebaseData(plantName: String) {
        val databaseReference = fDatabase.getReference(plantName)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.getValue<PlantFirebaseModel>()
                binding.tvDescriptionPlant.text =
                    result?.description ?: getString(R.string.capstone_description)
                binding.pgbCamera.setVisibility(false)
            }

            override fun onCancelled(error: DatabaseError) {
                FancyToast.makeText(
                    this@CameraActivity, getString(R.string.msg_error), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false
                ).show()
                binding.pgbCamera.setVisibility(false)
            }
        })
    }

    private fun View.setVisibility(state: Boolean) {
        if (state) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        leafModel.close()
    }

    companion object {
        const val PERMISSION_REQ_CODE = 111
    }
}