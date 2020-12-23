package com.ezafebrian.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezafebrian.core.data.source.local.dao.FeedsDao
import com.ezafebrian.core.data.source.local.entity.FeedsEntity

@Database(entities = [FeedsEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun dao(): FeedsDao
}