package com.mfahmi.mymedicineplantidentification.domain.usecase

import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import kotlinx.coroutines.flow.Flow

interface PlantUseCase {
    fun getBookmarkDataFromRepo(): Flow<List<PlantDomain>>
    suspend fun getPlantPictFromRepo(keyWord: String): Flow<ApiResponse<List<PlantPictResponse>>>
    suspend fun insertPlantToRepo(plantDomain: PlantDomain)
}