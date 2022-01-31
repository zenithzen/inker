package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val code: Int?,
    val data: Data?,
   /* val errors: List<Any>?,*/
    val message: String?,
    val success: Boolean?
)