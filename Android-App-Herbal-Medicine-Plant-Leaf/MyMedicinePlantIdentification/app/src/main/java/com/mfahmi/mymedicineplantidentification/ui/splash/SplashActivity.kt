package com.mfahmi.mymedicineplantidentification.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.ActivitySplashBinding
import com.mfahmi.mymedicineplantidentification.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.imageView.setAnimation(R.anim.anim_slide_down, 1500L, 0L)
        binding.tvSplashScreen.setAnimation(R.anim.anim_slide_up, 1000L, 0L)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }

    private fun View.setAnimation(animationId: Int, animTime: Long, startOffset: Long) {
        startAnimation(AnimationUtils.loadAnimation(context, animationId).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        })
    }
}