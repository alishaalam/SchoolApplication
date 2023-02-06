package com.app.schoolapplication.di

import android.app.Application
import androidx.room.Room
import com.app.schoolapplication.data.local.SchoolDatabase
import com.app.schoolapplication.data.repository.SchoolRepositoryImpl
import com.app.schoolapplication.domain.repository.SchoolRepository
import com.app.schoolapplication.network.SchoolsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// Repositories will live same as the activity that requires them
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSchoolDatabase(app: Application) : SchoolDatabase {
        return Room.databaseBuilder(
            app,
            SchoolDatabase::class.java,
            "school.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(schoolsApi: SchoolsApi, db: SchoolDatabase): SchoolRepository {
        return SchoolRepositoryImpl(schoolsApi, db)
    }
}