package com.app.schoolapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SchoolEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase : RoomDatabase() {
    abstract val dao: SchoolDao
}