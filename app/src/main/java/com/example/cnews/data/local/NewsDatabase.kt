package com.example.cnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cnews.data.local.databaseModels.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}