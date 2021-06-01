package com.mfahmi.mymedicineplantidentification.ui.main

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.ActivityMainBinding
import com.mfahmi.mymedicineplantidentification.ui.bookmark.BookmarkFragment
import com.mfahmi.mymedicineplantidentification.ui.camera.CameraActivity
import com.mfahmi.mymedicineplantidentification.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNav.background = null

        selectedFragment(HomeFragment())
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_bot_home -> selectedFragment(HomeFragment())
                R.id.nav_bot_user -> selectedFragment(BookmarkFragment())
            }
            true
        }
        binding.fabCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

    }

    private fun selectedFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frm_main, fragment)
            commit()
        }
    }
}