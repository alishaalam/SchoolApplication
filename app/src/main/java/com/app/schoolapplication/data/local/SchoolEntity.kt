package com.app.schoolapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SchoolEntity(
    val schoolName : String,
    val dbn : String,
    val readingScore : Int,
    val mathScore : Int,
    val writingScore : Int,
    @PrimaryKey val id: Int? = null
)