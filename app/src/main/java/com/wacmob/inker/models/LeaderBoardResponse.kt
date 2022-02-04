package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class LeaderBoardResponse(
    val code: Int?,
    val data: DataXXXX?,
  /*  val errors: List<Any>,*/
    val message: String?,
    val success: Boolean?
)