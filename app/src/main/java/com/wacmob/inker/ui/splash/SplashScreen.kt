package com.wacmob.inker.ui.splash

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.wacmob.inker.R
import com.wacmob.inker.databinding.ActivitySplashScreenBinding
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.ui.auth.AuthActivity
import com.wacmob.inker.ui.main.MainActivity
import com.wacmob.inker.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private val viewModel: SplashViewModel by viewModels()
    private val binding: ActivitySplashScreenBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        navigation()
        val videoUrl =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"

        binding.letsStart.setOnClickListener {
            if (!preferenceHandler.isLogged) {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()

            } else {

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }

        }

        //val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + com.wacmob.inker.R.raw.inker)
        val uri = Uri.parse(videoUrl)
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()

    }

    override fun onResume() {
        super.onResume()
    }

    fun navigation() {
        /* Handler(Looper.getMainLooper()).postDelayed(

             {
                 if (!preferenceHandler.isLogged) {
                     startActivity(Intent(this, AuthActivity::class.java))
                     finish()

                 } else {

                     startActivity(Intent(this, MainActivity::class.java))
                     finish()

                 }

             }, 4000L)*/

    }
}