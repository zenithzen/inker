package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class OtpResponse(
    val code: Int?,
    val `data`: DataX,

    val message: String?,
    val success: Boolean?,
)