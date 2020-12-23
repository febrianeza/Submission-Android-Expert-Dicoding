package com.ezafebrian.submissionexpert.di

import com.ezafebrian.core.domain.usecase.FeedsInteractor
import com.ezafebrian.core.domain.usecase.FeedsUseCase
import com.ezafebrian.submissionexpert.ui.detail.DetailViewModel
import com.ezafebrian.submissionexpert.ui.feeds.FeedsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FeedsUseCase> { FeedsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FeedsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}