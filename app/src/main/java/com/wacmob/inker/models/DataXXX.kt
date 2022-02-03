package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class DataXXX(
    /*val created_at: String,
    val deleted_at: Any,*/
    val id: Int?,
    val is_active: Int?,
    val name: String?,
    val slug: String?,
    var isSelected: Boolean = false
   /* val club_type_image_full_url: String?*/
    /* val updated_at: Any*/
)