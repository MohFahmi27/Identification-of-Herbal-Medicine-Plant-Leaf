package com.mfahmi.mymedicineplantidentification.ui.main

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mfahmi.mymedicineplantidentification.databinding.ActivityMainBinding
import com.mfahmi.mymedicineplantidentification.ui.camera.CameraActivity

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNav.background = null

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.frgContainerView.id) as NavHostFragment
        binding.bottomNav.setupWithNavController(navHostFragment.navController)
        binding.fabCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}