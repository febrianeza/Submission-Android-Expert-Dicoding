package com.ezafebrian.favorite.ui.detail

import androidx.lifecycle.ViewModel
import com.ezafebrian.core.domain.model.Feeds
import com.ezafebrian.core.domain.usecase.FeedsUseCase

class FavoriteDetailViewModel(private val feedsUseCase: FeedsUseCase): ViewModel() {
    fun setLikeFeeds(feeds: Feeds, newState: Boolean) = feedsUseCase.setLikePost(feeds, newState)
}