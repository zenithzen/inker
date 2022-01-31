package com.wacmob.inker.models

data class Course(
    val course_code: String,
    val course_image_full_url: String,
    val course_name: String,
    val created_at: String,
    val deleted_at: Any,
    val description: String,
    val id: Int,
    val image: String,
    val intro_video: IntroVideo,
    val introductory_video_id: Int,
    val is_active: Int,
    val is_featured: Int,
    val mrp_amount: Int,
    val net_amount: Int,
    val updated_at: String,
    val validity_days: Int
)