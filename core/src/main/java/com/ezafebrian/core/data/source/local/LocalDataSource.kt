package com.ezafebrian.core.data.source.local

import com.ezafebrian.core.data.source.local.dao.FeedsDao
import com.ezafebrian.core.data.source.local.entity.FeedsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: FeedsDao) {
    suspend fun insertPosts(model: List<FeedsEntity>) = dao.insertPosts(model)

    fun getPosts() : Flow<List<FeedsEntity>> = dao.getPosts()

    fun getLikedPosts() : Flow<List<FeedsEntity>> = dao.getLikedPosts()

    fun updateLikePost(model: FeedsEntity, state: Boolean) {
        model.isLiked = state
        dao.updateLikePost(model)
    }
}