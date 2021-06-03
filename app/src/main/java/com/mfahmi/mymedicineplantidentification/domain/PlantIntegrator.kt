package com.mfahmi.mymedicineplantidentification.domain

import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import kotlinx.coroutines.flow.Flow

class PlantIntegrator(private val plantRepositoryInterface: PlantRepositoryInterface) :
    PlantUseCase {
    override fun getBookmarkDataFromRepo(): Flow<List<PlantDomain>> =
        plantRepositoryInterface.getAllPlantsData()

    override suspend fun insertPlantToRepo(plantDomain: PlantDomain) {
        plantRepositoryInterface.saveDataPlantsToDB(plantDomain)
    }
}