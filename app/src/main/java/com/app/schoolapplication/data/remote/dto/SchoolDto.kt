package com.app.schoolapplication.data.remote.dto

import com.squareup.moshi.Json

data class SchoolDto(
    @Json(name = "school_name") val schoolName : String,
    val dbn : String,
    @Json (name = "sat_critical_reading_avg_score") val readingScore : String,
    @Json (name = "sat_math_avg_score") val mathScore : String,
    @Json (name = "sat_writing_avg_score") val writingScore : String,
)
