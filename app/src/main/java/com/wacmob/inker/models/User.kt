package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    /* val activation_code: Any,
     val created_at: String,
     val date_of_birth: Any,
     val date_of_join: Any,
     val deleted_at: Any,*/
    val id: Int,
    val email: String?,
    /* val email_verified_at: Any,
     val failed_logins: Any,
     val full_mobile_number: Any,
     val gender_id: Any,
 val is_active: Int,
     val is_logged_in: Int,
     val last_password_change_timestamp: Any,*/
    val mobile: String?,
    val name: String?,
    /* val otp_expired_at: Any,
     val otp_generated_at: Any,*/
    val phone_country_code: String,
    // val player_id: String?,
    val profile_photo: String?,
    // val profile_video: Any,
    /*   val profiles: List<Profile>,
       val reset_token: Any,
       val updated_at: String,*/
    // val user_bio: String?,
    val user_code: String?,
    val user_type_id: Int?,
)