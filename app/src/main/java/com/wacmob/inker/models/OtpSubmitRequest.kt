package com.wacmob.inker.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtpSubmitRequest(
    @SerialName("otp")
    val otp: String?,
    @SerialName("user_code")
    val user_code: String?,
    @SerialName("mobile")
    val mobile: String?,
    @SerialName("phone_country_code")
    val phone_country_code: String?
)

