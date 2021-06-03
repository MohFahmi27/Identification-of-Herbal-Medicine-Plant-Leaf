package com.mfahmi.mymedicineplantidentification.domain.usecase

import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import kotlinx.coroutines.flow.Flow

interface PlantUseCase {
    fun getBookmarkDataFromRepo(): Flow<List<PlantDomain>>
    suspend fun insertPlantToRepo(plantDomain: PlantDomain)
}