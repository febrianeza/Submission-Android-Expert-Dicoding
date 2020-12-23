package com.ezafebrian.favorite.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ezafebrian.core.domain.usecase.FeedsUseCase

class FavoriteViewModel(private val feedsUseCase: FeedsUseCase): ViewModel() {
    val likedPost = feedsUseCase.getLikedPost().asLiveData()
}