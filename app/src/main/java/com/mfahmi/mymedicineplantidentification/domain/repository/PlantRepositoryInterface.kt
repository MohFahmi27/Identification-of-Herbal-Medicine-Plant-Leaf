package com.mfahmi.mymedicineplantidentification.domain.repository

import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import kotlinx.coroutines.flow.Flow

interface PlantRepositoryInterface {
    fun getAllPlantsData(): Flow<List<PlantDomain>>
    suspend fun getDataPict(pictSearch: String): Flow<ApiResponse<List<PlantPictResponse>>>
    suspend fun saveDataPlantsToDB(plantDomain: PlantDomain)
}