package com.ezafebrian.submissionexpert.ui.detail

import androidx.lifecycle.ViewModel
import com.ezafebrian.core.domain.model.Feeds
import com.ezafebrian.core.domain.usecase.FeedsUseCase

class DetailViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel() {
    fun setLikeFeeds(feeds: Feeds, newState: Boolean) = feedsUseCase.setLikePost(feeds, newState)
}