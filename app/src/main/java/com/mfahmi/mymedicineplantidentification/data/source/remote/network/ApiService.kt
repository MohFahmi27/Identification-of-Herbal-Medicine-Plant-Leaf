package com.mfahmi.mymedicineplantidentification.data.source.remote.network

import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiCredentials.API_KEY
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.ResponseItemsPlant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getHerbalPlantsPict(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") keyWord: String
    ): ResponseItemsPlant
}