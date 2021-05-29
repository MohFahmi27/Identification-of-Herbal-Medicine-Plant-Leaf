package com.mfahmi.mymedicineplantidentification.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mfahmi.mymedicineplantidentification.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {
    private val binding: ActivityCameraBinding by viewBinding()
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            bitmap -> binding.imgCameraCapture.setImageBitmap(bitmap)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnCloseCameraAct.setOnClickListener { finish() }

        binding.btnAddPhoto.isEnabled = false

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

        binding.btnAddPhoto.setOnClickListener {
            takePicture.launch(null)
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

    companion object {
        const val PERMISSION_REQ_CODE = 111
    }
}