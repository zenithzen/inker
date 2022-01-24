package com.wacmob.inker.ui.main

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.wacmob.inker.R
import com.wacmob.inker.databinding.ActivityAuthBinding
import com.wacmob.inker.databinding.ActivityMainBinding
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

    val bindings:ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }




}