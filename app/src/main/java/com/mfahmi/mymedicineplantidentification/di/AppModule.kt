package com.mfahmi.mymedicineplantidentification.di

import com.mfahmi.mymedicineplantidentification.domain.PlantInteractor
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import com.mfahmi.mymedicineplantidentification.ui.bookmark.BookmarkViewModel
import com.mfahmi.mymedicineplantidentification.ui.camera.CameraViewModel
import com.mfahmi.mymedicineplantidentification.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PlantUseCase> { PlantInteractor(get()) }
}

val viewModelModule = module {
    viewModel { CameraViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}