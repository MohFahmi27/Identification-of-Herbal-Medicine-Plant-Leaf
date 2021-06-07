package com.mfahmi.mymedicineplantidentification.data.source.remote

import android.util.Log
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiResponse.*
import com.mfahmi.mymedicineplantidentification.data.source.remote.network.ApiService
import com.mfahmi.mymedicineplantidentification.data.source.remote.response.PlantPictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllPlantPictFromAPI(queryPict: String): Flow<ApiResponse<List<PlantPictResponse>>> =
        flow {
            try {
                val response = apiService.getHerbalPlantsPict(keyWord = queryPict)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) emit(Success(response.results)) else emit(Empty)
                Log.d("RemoteDataSource", "getAllPlantPictFromAPI: ${response.results}")
            } catch (e: Exception) {
                emit(Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}