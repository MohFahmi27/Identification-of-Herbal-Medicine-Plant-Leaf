package com.mfahmi.mymedicineplantidentification.data

import com.mfahmi.mymedicineplantidentification.data.source.local.LocalDataSource
import com.mfahmi.mymedicineplantidentification.data.source.remote.RemoteDataSource
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import com.mfahmi.mymedicineplantidentification.utils.DataMapper.mapDomainToEntity
import com.mfahmi.mymedicineplantidentification.utils.DataMapper.mapEntitiesToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantsRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : PlantRepositoryInterface {
    override fun getAllPlantsData(): Flow<List<PlantDomain>> =
        localDataSource.getPlantsDataFromDB().map { mapEntitiesToDomain(it) }

    override suspend fun getDataPict(pictSearch: String): Flow<ApiResponse<List<PlantPictResponse>>> {
        return remoteDataSource.getAllPlantPictFromAPI(pictSearch)
    }

    override suspend fun saveDataPlantsToDB(plantDomain: PlantDomain) {
        localDataSource.insertPlantsToDB(mapDomainToEntity(plantDomain))
    }

}