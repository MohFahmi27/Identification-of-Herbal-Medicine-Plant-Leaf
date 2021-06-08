package com.mfahmi.mymedicineplantidentification.domain

import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import com.mfahmi.mymedicineplantidentification.domain.usecase.PlantUseCase
import kotlinx.coroutines.flow.Flow

class PlantInteractor(private val plantRepositoryInterface: PlantRepositoryInterface) :
    PlantUseCase {
    override fun getBookmarkDataFromRepo(): Flow<List<PlantDomain>> =
        plantRepositoryInterface.getAllPlantsData()

    override suspend fun getPlantPictFromRepo(keyWord: String): Flow<ApiResponse<List<PlantPictResponse>>> {
        return plantRepositoryInterface.getDataPict(keyWord)
    }

    override suspend fun insertPlantToRepo(plantDomain: PlantDomain) {
        plantRepositoryInterface.saveDataPlantsToDB(plantDomain)
    }
}