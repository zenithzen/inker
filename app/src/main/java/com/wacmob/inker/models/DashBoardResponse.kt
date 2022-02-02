package com.wacmob.inker.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashBoardResponse(
    val code: Int?,
    val data: DataXX?,
    val message: String?,
    val success: Boolean?
)

/*
val code: Int?,
val data: DataXX?,
val message: String?,
val success: Boolean?*/
