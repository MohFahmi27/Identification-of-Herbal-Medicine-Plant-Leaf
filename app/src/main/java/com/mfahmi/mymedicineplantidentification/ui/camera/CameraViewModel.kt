package com.mfahmi.mymedicineplantidentification.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfahmi.mymedicineplantidentification.data.DataDummy
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.models.Plants
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraViewModel(private val plantUseCase: PlantUseCase): ViewModel() {
    fun insertPlantData(plantDomain: PlantDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            plantUseCase.insertPlantToRepo(plantDomain)
        }
    }
    val plantsDummy: LiveData<List<Plants>> = DataDummy.getPlantsDummy()
}