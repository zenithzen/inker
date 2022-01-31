package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    /*val full_mobile_number: Any?,*/
    val id: Int?,
    val mobile: String?,
    val phone_country_code: String?,
    val updated_at: String?,
    val user_code: String?
)