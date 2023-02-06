package com.app.schoolapplication.repository

import com.app.schoolapplication.domain.model.School
import com.app.schoolapplication.domain.repository.SchoolRepository
import com.app.schoolapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : SchoolRepository {

    var schoolsToInsert = emptyList<School>()

    override suspend fun getSchoolsList(fetchFromRemote: Boolean): Flow<Resource<List<School>>> {
        return flow { emit(Resource.Success(schoolsToInsert))}
    }
}