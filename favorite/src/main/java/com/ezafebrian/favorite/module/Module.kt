package com.ezafebrian.favorite.module

import com.ezafebrian.favorite.ui.detail.FavoriteDetailViewModel
import com.ezafebrian.favorite.ui.list.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}