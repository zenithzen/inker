package com.wacmob.inker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wacmob.inker.preferences.PreferenceHandler

import com.wacmob.inker.utils.StylishToastyUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceHandler.userToken = "Hello world"
        stylishToastyUtils.showSuccessMessage(preferenceHandler.userToken)




    }




}