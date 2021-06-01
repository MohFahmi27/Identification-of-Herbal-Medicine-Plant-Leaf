package com.mfahmi.mymedicineplantidentification.domain.repository

import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import kotlinx.coroutines.flow.Flow

interface PlantRepositoryInterface {
    fun getAllPlantsData(): Flow<List<PlantDomain>>
    suspend fun saveDataPlantsToDB(plantDomain: PlantDomain)
}