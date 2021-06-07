package com.mfahmi.mymedicineplantidentification.domain.di

import androidx.room.Room
import com.mfahmi.mymedicineplantidentification.data.PlantsRepository
import com.mfahmi.mymedicineplantidentification.data.source.local.LocalDataSource
import com.mfahmi.mymedicineplantidentification.data.source.local.db.PlantsDatabase
import com.mfahmi.mymedicineplantidentification.data.source.remote.RemoteDataSource
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiCredentials.BASE_URL
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiService
import com.mfahmi.mymedicineplantidentification.domain.repository.PlantRepositoryInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<PlantsDatabase>().plantsDao() }
    single {
        Room.databaseBuilder(androidContext(), PlantsDatabase::class.java, "bookmark_plant.db")
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<PlantRepositoryInterface> { PlantsRepository(get(), get()) }
}