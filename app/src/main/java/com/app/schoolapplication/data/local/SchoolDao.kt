package com.app.schoolapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchools(
        schoolEntity : List<SchoolEntity>
    )

    @Query("DELETE FROM schoolentity")
    suspend fun clearSchools()

    @Query("SELECT * FROM schoolentity")
    suspend fun getSchoolsList() : List<SchoolEntity>
}