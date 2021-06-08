package com.mfahmi.mymedicineplantidentification.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseItemsPlant(
    @SerializedName("hits")
    val results: List<PlantPictResponse>
)
