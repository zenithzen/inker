package com.wacmob.inker.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject


/** Created by Jishnu P Dileep on 26-05-2021 */

class PreferenceHandler @Inject constructor(
    val sharedPreferences: SharedPreferences,
) {
    var userToken: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit { putString("token", value) }

    var userId: String
        get() = sharedPreferences.getString("userId", "") ?: ""
        set(value) = sharedPreferences.edit { putString("userId", value) }

    var userName: String
        get() = sharedPreferences.getString("userName", "") ?: ""
        set(value) = sharedPreferences.edit { putString("userName", value) }
    var profilePic: String
        get() = sharedPreferences.getString("profilePic", "") ?: ""
        set(value) = sharedPreferences.edit { putString("profilePic", value) }

    var mobileNumber: String
        get() = sharedPreferences.getString("mobileNumber", "") ?: ""
        set(value) = sharedPreferences.edit { putString("mobileNumber", value) }
    var countryCode: String
        get() = sharedPreferences.getString("countryCode", "") ?: ""
        set(value) = sharedPreferences.edit { putString("countryCode", value) }

    var userCode: String
        get() = sharedPreferences.getString("userCode", "") ?: ""
        set(value) = sharedPreferences.edit { putString("userCode", value) }

    var isOnBoardingCompleted: Boolean
        get() = sharedPreferences.getBoolean("isOnBoardingCompleted", false)
        set(value) = sharedPreferences.edit() { putBoolean("isOnBoardingCompleted", value) }

    var isLogged: Boolean
        get() = sharedPreferences.getBoolean("isLogged", false)
        set(value) = sharedPreferences.edit() { putBoolean("isLogged", value) }


}