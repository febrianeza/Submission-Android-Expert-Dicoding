package com.ezafebrian.core.domain.repository

import com.ezafebrian.core.data.Resource
import com.ezafebrian.core.domain.model.Feeds
import kotlinx.coroutines.flow.Flow

interface IFeedsRepository {
    fun getPosts(): Flow<Resource<List<Feeds>>>
    fun getLikedPost(): Flow<List<Feeds>>
    fun setLikePost(feeds: Feeds, state: Boolean)
}