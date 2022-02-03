package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class ClubListResponse(
    val code: Int?,
    val data: List<DataXXX>?,
    /*  val errors: List<Any>,*/
    val message: String?,
    val success: Boolean?

)