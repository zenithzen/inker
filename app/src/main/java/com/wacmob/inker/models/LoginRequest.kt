package com.wacmob.inker.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("mobile")
    val userName: String?,
    @SerialName("phone_country_code")
    val email: String?
)