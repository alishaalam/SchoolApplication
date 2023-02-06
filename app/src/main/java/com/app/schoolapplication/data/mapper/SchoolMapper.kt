package com.app.schoolapplication.data.mapper

import com.app.schoolapplication.data.local.SchoolEntity
import com.app.schoolapplication.data.remote.dto.SchoolDto
import com.app.schoolapplication.domain.model.School

fun SchoolEntity.toSchool() : School {
    return School(
        schoolName = schoolName,
        dbn = dbn,
        writingScore = writingScore,
        readingScore = readingScore,
        mathScore = mathScore,
    )
}

fun School.toSchoolEntity() : SchoolEntity {
    return SchoolEntity(
        schoolName = schoolName,
        dbn = dbn,
        writingScore = writingScore,
        readingScore = readingScore,
        mathScore = mathScore,
    )
}

fun SchoolDto.toSchool() : School {
    return School(
        schoolName = schoolName,
        dbn = dbn,
        writingScore = writingScore.toIntOrNull() ?: 0,
        readingScore = readingScore.toIntOrNull() ?: 0,
        mathScore = mathScore.toIntOrNull() ?: 0
    )
}

fun SchoolDto.toSchoolEntity() : SchoolEntity {
    return SchoolEntity(
        schoolName = schoolName,
        dbn = dbn,
        writingScore = writingScore.toIntOrNull() ?: 0,
        readingScore = readingScore.toIntOrNull() ?: 0,
        mathScore = mathScore.toIntOrNull() ?: 0
    )
}