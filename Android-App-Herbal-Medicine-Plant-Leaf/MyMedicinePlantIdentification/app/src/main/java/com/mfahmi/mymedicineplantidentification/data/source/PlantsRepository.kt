package com.mfahmi.mymedicineplantidentification.data.source

import com.mfahmi.mymedicineplantidentification.data.source.local.LocalDataSource
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import com.mfahmi.mymedicineplantidentification.utils.DataMapper.mapDomainToEntity
import com.mfahmi.mymedicineplantidentification.utils.DataMapper.mapEntitiesToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantsRepository(private val localDataSource: LocalDataSource) : PlantRepositoryInterface {
    override fun getAllPlantsData(): Flow<List<PlantDomain>> =
        localDataSource.getPlantsDataFromDB().map { mapEntitiesToDomain(it) }

    override suspend fun saveDataPlantsToDB(plantDomain: PlantDomain) {
        localDataSource.insertPlantsToDB(mapDomainToEntity(plantDomain))
    }

}