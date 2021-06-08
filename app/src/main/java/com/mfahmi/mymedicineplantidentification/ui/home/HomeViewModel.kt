package com.mfahmi.mymedicineplantidentification.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mfahmi.mymedicineplantidentification.data.DataDummy
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import com.mfahmi.mymedicineplantidentification.domain.models.Plants
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import kotlinx.coroutines.flow.collect
import java.net.URLEncoder

class HomeViewModel(private val plantUseCase: PlantUseCase) : ViewModel() {
    private val queryUrl: String = URLEncoder.encode("green plants", "utf-8")

    val plant: LiveData<ApiResponse<List<PlantPictResponse>>> = liveData {
        plantUseCase.getPlantPictFromRepo(queryUrl).collect {
            emit(it)
        }
    }
    val plantsDummy: LiveData<List<Plants>> = DataDummy.getPlantsDummy()
}