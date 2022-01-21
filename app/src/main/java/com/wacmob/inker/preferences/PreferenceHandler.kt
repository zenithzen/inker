package com.wacmob.inker.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject


/** Created by Jishnu P Dileep on 26-05-2021 */

class PreferenceHandler @Inject constructor(
    val sharedPreferences: SharedPreferences
) {
    var userToken: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit { putString("token", value) }

    var isOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean("isOnBoardingCompleted", false)
        set(value) = sharedPreferences.edit() { putBoolean("isOnBoardingCompleted", value) }

    var isLogged: Boolean
        get() = sharedPreferences.getBoolean("isLogged", false)
        set(value) = sharedPreferences.edit() { putBoolean("isLogged", value) }
}