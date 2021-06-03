package com.mfahmi.mymedicineplantidentification.data.source.local

import com.mfahmi.mymedicineplantidentification.data.source.local.db.PlantsDao
import com.mfahmi.mymedicineplantidentification.data.source.local.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val plantsDao: PlantsDao) {
    fun getPlantsDataFromDB(): Flow<List<PlantEntity>> = plantsDao.getAllPlants()

    suspend fun insertPlantsToDB(plantEntity: PlantEntity) =
        plantsDao.insertBookmarkPlants(plantEntity)
}