package com.mfahmi.mymedicineplantidentification.domain.di

import androidx.room.Room
import com.mfahmi.mymedicineplantidentification.data.source.PlantsRepository
import com.mfahmi.mymedicineplantidentification.data.source.local.LocalDataSource
import com.mfahmi.mymedicineplantidentification.data.source.local.db.PlantsDatabase
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<PlantsDatabase>().plantsDao() }
    single {
        Room.databaseBuilder(androidContext(), PlantsDatabase::class.java, "bookmark_plant.db")
            .fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single<PlantRepositoryInterface> { PlantsRepository(get()) }
}