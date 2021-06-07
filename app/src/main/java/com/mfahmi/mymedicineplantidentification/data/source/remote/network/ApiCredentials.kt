package com.mfahmi.mymedicineplantidentification.data.source.remote.network

import com.mfahmi.mymedicineplantidentification.BuildConfig

object ApiCredentials {
    val API_KEY by lazy { BuildConfig.API_KEY_PIXABAY }
    val BASE_URL by lazy { "https://pixabay.com/" }
}