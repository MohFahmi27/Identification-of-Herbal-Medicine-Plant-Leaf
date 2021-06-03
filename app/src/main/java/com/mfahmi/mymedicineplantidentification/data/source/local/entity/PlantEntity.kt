package com.mfahmi.mymedicineplantidentification.data.source.local.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val img: Bitmap
)