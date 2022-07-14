package com.example.cnews.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cnews.data.local.databaseModels.NewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("select * from newsentity")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news:List<NewsEntity>)
}