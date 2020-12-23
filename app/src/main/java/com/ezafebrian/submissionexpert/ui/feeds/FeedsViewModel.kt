package com.ezafebrian.submissionexpert.ui.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ezafebrian.core.domain.usecase.FeedsUseCase

class FeedsViewModel(feedsUseCase: FeedsUseCase) : ViewModel() {
    val feeds = feedsUseCase.getPosts().asLiveData()
}