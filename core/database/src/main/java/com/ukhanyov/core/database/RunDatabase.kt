package com.ukhanyov.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ukhanyov.core.database.dao.RunDao
import com.ukhanyov.core.database.dao.RunPendingSyncDao
import com.ukhanyov.core.database.entity.DeletedRunSyncEntity
import com.ukhanyov.core.database.entity.RunEntity
import com.ukhanyov.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class,
    ],
    version = 2,
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
}