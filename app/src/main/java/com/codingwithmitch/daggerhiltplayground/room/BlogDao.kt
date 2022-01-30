package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
     fun get(): Flow<List<BlogCacheEntity>>
}