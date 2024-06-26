package com.ukhanyov.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ukhanyov.core.database.dao.RunDao
import com.ukhanyov.core.database.entity.RunEntity

@Database(
    entities = [RunEntity::class],
    version = 1
)
abstract class RunDatabase: RoomDatabase() {

    abstract val runDao: RunDao
}