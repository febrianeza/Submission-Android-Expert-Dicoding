package com.ezafebrian.core.domain.usecase

import com.ezafebrian.core.data.Resource
import com.ezafebrian.core.domain.model.Feeds
import com.ezafebrian.core.domain.repository.IFeedsRepository
import kotlinx.coroutines.flow.Flow

class FeedsInteractor(private val feedsRepo: IFeedsRepository) : FeedsUseCase {
    override fun getPosts(): Flow<Resource<List<Feeds>>> = feedsRepo.getPosts()

    override fun getLikedPost(): Flow<List<Feeds>> = feedsRepo.getLikedPost()

    override fun setLikePost(feeds: Feeds, state: Boolean) = feedsRepo.setLikePost(feeds, state)
}