package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class DataXXXX(
    val club: Club?,
    val leaderboard: List<Leaderboard>?,
    val me: Me?
)