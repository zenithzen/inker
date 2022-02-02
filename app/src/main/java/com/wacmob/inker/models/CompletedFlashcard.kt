package com.wacmob.inker.models

data class CompletedFlashcard(
    val card_code: String,
    val created_at: String,
    val deleted_at: Any,
    val flash_card_desc: String,
    val flash_card_file: String,
    val flash_card_file_full_url: String,
    val flash_card_image: String,
    val flash_card_image_full_url: String,
    val flash_card_title: String,
    val id: Int,
    val is_active: Int,
    val is_featured: Int,
    val laravel_through_key: Int,
    val recognization_code: String,
    val type_id: Int,
    val updated_at: String
)