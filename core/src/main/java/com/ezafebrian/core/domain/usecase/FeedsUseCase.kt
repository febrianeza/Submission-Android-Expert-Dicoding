package com.ezafebrian.core.domain.usecase

import com.ezafebrian.core.data.Resource
import com.ezafebrian.core.domain.model.Feeds
import kotlinx.coroutines.flow.Flow

interface FeedsUseCase {
    fun getPosts(): Flow<Resource<List<Feeds>>>
    fun getLikedPost(): Flow<List<Feeds>>
    fun setLikePost(feeds: Feeds, state: Boolean)
}