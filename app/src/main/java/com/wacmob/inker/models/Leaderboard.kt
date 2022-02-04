package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class Leaderboard(
    val game_points: Int?,
    val name: String?,
    val position: Int?,
    val profile_id: Int?,
    val profile_photo_full_url: String?,
    var isSelf: Boolean = false,
)