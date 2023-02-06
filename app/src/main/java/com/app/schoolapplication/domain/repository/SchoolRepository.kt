package com.app.schoolapplication.domain.repository

import com.app.schoolapplication.domain.model.School
import com.app.schoolapplication.util.Resource
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun getSchoolsList(
        fetchFromRemote : Boolean
    ): Flow<Resource<List<School>>>
}

