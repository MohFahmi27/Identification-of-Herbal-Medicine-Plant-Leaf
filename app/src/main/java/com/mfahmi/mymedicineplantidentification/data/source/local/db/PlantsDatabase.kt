package com.mfahmi.mymedicineplantidentification.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mfahmi.mymedicineplantidentification.data.source.local.entity.PlantEntity
import com.mfahmi.mymedicineplantidentification.utils.ConvertersHelper

@Database(entities = [PlantEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConvertersHelper::class)
abstract class PlantsDatabase: RoomDatabase() {
    abstract fun plantsDao(): PlantsDao
}