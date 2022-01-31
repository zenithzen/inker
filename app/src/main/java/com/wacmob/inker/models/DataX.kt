package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    val access_token: String?,
    val user: User?
)