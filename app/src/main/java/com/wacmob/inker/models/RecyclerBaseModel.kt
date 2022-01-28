package com.wacmob.inker.models

import java.util.logging.Level

data class RecyclerBaseModel(
    val id:Int=0,
    val level:Int?,
    val titleData: String?,
    val levelList: List<LevelData>?,
    val performanceList: List<Perfomance>?,
)

data class LevelData(val id:Int?,val images: Int?)
data class Perfomance(
    val titleData: String?,
    val images: Int?,
    val count: String?,
    val color: String?,
)
