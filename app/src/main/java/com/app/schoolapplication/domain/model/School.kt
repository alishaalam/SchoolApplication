package com.app.schoolapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class School(
    val schoolName : String,
    val dbn : String,
    val readingScore : Int,
    val writingScore : Int,
    val mathScore : Int,
): Parcelable