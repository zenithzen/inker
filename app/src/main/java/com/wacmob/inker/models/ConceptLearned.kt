package com.wacmob.inker.models

import kotlinx.serialization.Serializable

@Serializable
data class ConceptLearned(
   /* val created_at: Any,
    val deleted_at: Any,*/
    val description: String?,
  /*  val icon: String,*/
    val id: Int?,
  /*  val is_active: Int,
    val pivot: PivotX,*/
    val skill_icon_full_url: String?,
    val type: String?,
   /* val updated_at: String*/
)