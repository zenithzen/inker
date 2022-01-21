package com.wacmob.inker.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.wacmob.inker.R
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.ui.auth.AuthActivity
import com.wacmob.inker.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        navigation()
    }

    fun navigation() {
        Handler(Looper.getMainLooper()).postDelayed(

            {
                if (!preferenceHandler.isLogged) {
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()

                } else {

                }

            }, 2000L)

    }
}