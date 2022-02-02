package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class DataXX(
    /* val about_me: Any,
     val activity_correction_streak: String,
     val activity_keywords_gained: Any,
     val alt_email: Any,
     val backed_sound: Int,*/
    val badges: List<Badge>?,
    val badges_count: Int?,
    /* val board_id: Any,
     val click_sound: Int,*/
    val completed_cues_count: Int?,
    /* val completed_flashcards: List<CompletedFlashcard>,*/
    val completed_flashcards_count: Int?,
    val concept_learned: ConceptLearned?,
    val concepts_learned: Int?,
    /* val created_at: String,
     val deleted_at: Any,
     val dob: Any,*/
    val email: String?,
    val full_mobile_number: String?,
    val game_points: Int?,
    /*  val gender_id: Any,*/
    val hours_spent_on_cue: Int?,
    val id: Int?,
    val is_active: Int?,
    val is_mobile_verified: Int?,
    val language_id: Int?,
    val minutes_spent_on_cue: String?,
    val mobile_no: String?,
    val name: String?,
    /* val notifications_sound: Int,
     val otp_expired_at: Any,
     val otp_generated_at: Any,
     val pace_id: Int,
     val phone_country_code: Any,
     val points_spend: Int,
     val previous_club_position: Any,*/
    val profile_code: String?,
    /*  val profile_join_date: String?,*/
    val profile_photo: String?,
    val profile_photo_full_url: String?,
    /* val profile_video: Any,
     val school_name: Any,*/
    val streak_count: Int?,
    val total_cues: Int?,
    val total_flashcards: Int?,
    val total_points: Int?,
    /* val updated_at: String,*/
    val user_id: Int?,
)