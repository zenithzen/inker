package com.wacmob.inker.models

data class IntroVideo(
    val category_id: Int,
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val is_active: Int,
    val is_featured: Int,
    val language_id: Int,
    val recognization_code: String,
    val thumbnail_full_url: String,
    val thumbnail_image: String,
    val updated_at: String,
    val video_code: String,
    val video_description: String,
    val video_title: String,
    val video_url: String
)