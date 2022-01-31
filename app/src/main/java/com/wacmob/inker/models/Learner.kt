package com.wacmob.inker.models

data class Learner(
    val course: Course,
    val course_id: Int,
    val current_cue: CurrentCue,
    val has_started: Int,
    val id: Int,
    val profile_id: Int
)