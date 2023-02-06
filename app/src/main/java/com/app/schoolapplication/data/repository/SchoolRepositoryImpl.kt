package com.app.schoolapplication.data.repository

import android.util.Log
import com.app.schoolapplication.data.local.SchoolDatabase
import com.app.schoolapplication.data.mapper.toSchool
import com.app.schoolapplication.data.mapper.toSchoolEntity
import com.app.schoolapplication.domain.model.School
import com.app.schoolapplication.domain.repository.SchoolRepository
import com.app.schoolapplication.network.SchoolsApi
import com.app.schoolapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val api: SchoolsApi,
    db: SchoolDatabase,
) : SchoolRepository {

    private val dao = db.dao

    override suspend fun getSchoolsList(
        fetchFromRemote: Boolean,
    ): Flow<Resource<List<School>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localSchoolsList = dao.getSchoolsList()
            emit(Resource.Success(
                data = localSchoolsList.map { it.toSchool() }
            ))

            val isDbEmpty = localSchoolsList.isEmpty()
            val loadDataFromCache = !isDbEmpty && !fetchFromRemote
            if (loadDataFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteSchoolsList = try {
                api.fetchSchoolListDetails()
            } catch (e: IOException) {
                Log.e(TAG, e.localizedMessage!!)
                emit(Resource.Error( "An unexpected error occurred."))
                null
            } catch (e: HttpException) {
                Log.e(TAG, e.localizedMessage!!)
                emit(Resource.Error(
                    "Couldn't reach server. Please check your internet connection."))
                null
            }

                remoteSchoolsList?.let { list ->
                    dao.clearSchools()
                    dao.insertSchools(
                        list.map { it.toSchoolEntity() }
                    )
                    emit(Resource.Success(
                        data = dao.getSchoolsList().map { it.toSchool() }
                    ))
                    Log.d("Alisha", "Loading 3 remoteStocksList")
                    emit(Resource.Loading( isLoading = false))
                }

        }
    }

    companion object {
        private val TAG = SchoolRepositoryImpl::class.qualifiedName
    }
}