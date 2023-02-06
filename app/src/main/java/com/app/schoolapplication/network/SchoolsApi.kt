package com.app.schoolapplication.network

import com.app.schoolapplication.data.remote.dto.SchoolDto
import retrofit2.http.GET

interface SchoolsApi {

    @GET("s3k6-pzi2.json")
    suspend fun fetchSchoolsList() : List<SchoolDto>

    @GET("f9bf-2cp4.json")
    suspend fun fetchSchoolListDetails() : List<SchoolDto>


}