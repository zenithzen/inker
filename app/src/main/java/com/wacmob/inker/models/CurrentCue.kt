package com.wacmob.inker.models

data class CurrentCue(
    val additional_details: Any,
    val created_at: String,
    val deleted_at: Any,
    val display_name: String,
    val id: Int,
    val learner_id: Int,
    val loggable_id: Int,
    val loggable_type: String,
    val status: Int,
    val updated_at: String
)