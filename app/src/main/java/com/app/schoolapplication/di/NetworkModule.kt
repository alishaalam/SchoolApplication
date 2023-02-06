package com.app.schoolapplication.di

import com.app.schoolapplication.network.SchoolsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    @Provides
    @Singleton
    fun provideMoshiInstance(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    /**
     * Build a retrofit object using a Moshi converter with Moshi object.
     */
    @Provides
    @Singleton
    fun provideRetrofitInstance(moshi : Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideSchoolsApi(retrofit: Retrofit): SchoolsApi {
        return retrofit.create(SchoolsApi::class.java)
    }
}
