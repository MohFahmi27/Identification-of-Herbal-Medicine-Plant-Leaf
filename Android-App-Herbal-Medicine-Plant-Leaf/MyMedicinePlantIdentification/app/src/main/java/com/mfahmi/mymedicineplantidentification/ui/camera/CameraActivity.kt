package com.mfahmi.mymedicineplantidentification.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mfahmi.mymedicineplantidentification.databinding.ActivityCameraBinding
import com.mfahmi.mymedicineplantidentification.ml.LeafModel
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.model.Model

class CameraActivity : AppCompatActivity() {
    private val binding: ActivityCameraBinding by viewBinding()
    private var bitmapResult: Bitmap? = null
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            run {
                binding.imgCameraCapture.setImageBitmap(bitmap)
                bitmapResult = bitmap
            }
        }
    private val leafModel: LeafModel by lazy {
        val options = if (CompatibilityList().isDelegateSupportedOnThisDevice) {
            Model.Options.Builder().setDevice(Model.Device.GPU).build()
        } else {
            Model.Options.Builder().setNumThreads(4).build()
        }
        LeafModel.newInstance(this, options)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnCloseCameraAct.setOnClickListener { finish() }
        binding.bottomAppBarCamera.background = null
        binding.btnAddPhoto.isEnabled = false

        checkPermissionCam()
        binding.btnAddPhoto.setOnClickListener {
            takePicture.launch(null)
        }
        binding.btnAnalyzePhoto.setOnClickListener {
            setResultAnalyze()
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

    private fun setResultAnalyze() {
        bitmapResult?.let {
            val outputs = leafModel.process(TensorImage.fromBitmap(bitmapResult))
            val probability =
                outputs.probabilityAsCategoryList.apply { sortByDescending { it.score } }
                    .take(1)
            probability.forEach {
                with(binding) {
                    tvTitlePlants.setVisibility(true)
                    tvDescriptionTitle.setVisibility(true)
                    tvDescriptionPlant.setVisibility(true)
                    btnSaveBookmark.setVisibility(true)
                    tvTitlePlants.text = it.label
                }
            }
        }
    }

    private fun View.setVisibility(state: Boolean) {
        if (state) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }

    companion object {
        const val PERMISSION_REQ_CODE = 111
    }
}