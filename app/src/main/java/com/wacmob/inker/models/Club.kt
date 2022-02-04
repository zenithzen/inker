package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val id: Int?,
    val name: String?
)