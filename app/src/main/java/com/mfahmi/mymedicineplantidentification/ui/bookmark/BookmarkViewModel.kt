package com.mfahmi.mymedicineplantidentification.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase

class BookmarkViewModel(plantUseCase: PlantUseCase): ViewModel() {
    val plantsBookmark = plantUseCase.getBookmarkDataFromRepo().asLiveData()
}