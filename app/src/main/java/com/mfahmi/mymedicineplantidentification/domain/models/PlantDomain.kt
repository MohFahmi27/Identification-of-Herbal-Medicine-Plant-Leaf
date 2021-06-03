package com.mfahmi.mymedicineplantidentification.domain.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantDomain(
    val title: String,
    val description: String,
    val date: String,
    val img: Bitmap
) : Parcelable