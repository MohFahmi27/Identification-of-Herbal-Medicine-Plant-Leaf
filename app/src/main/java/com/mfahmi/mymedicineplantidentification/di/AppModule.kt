package com.mfahmi.mymedicineplantidentification.di

import com.mfahmi.mymedicineplantidentification.domain.PlantIntegrator
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import com.mfahmi.mymedicineplantidentification.ui.bookmark.BookmarkViewModel
import com.mfahmi.mymedicineplantidentification.ui.camera.CameraViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PlantUseCase> { PlantIntegrator(get()) }
}

val viewModelModule = module {
    viewModel { CameraViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}