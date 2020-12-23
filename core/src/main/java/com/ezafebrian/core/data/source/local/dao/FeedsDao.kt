package com.ezafebrian.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ezafebrian.core.data.source.local.entity.FeedsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedsDao {
    @Insert
    suspend fun insertPosts(posts: List<FeedsEntity>)

    @Query("SELECT * FROM feeds_table")
    fun getPosts(): Flow<List<FeedsEntity>>

    @Query("SELECT * FROM feeds_table WHERE isLiked = 1")
    fun getLikedPosts(): Flow<List<FeedsEntity>>

    @Update
    fun updateLikePost(post: FeedsEntity)
}