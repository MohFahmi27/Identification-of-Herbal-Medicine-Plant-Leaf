package com.mfahmi.mymedicineplantidentification.utils

import com.mfahmi.mymedicineplantidentification.data.source.local.entity.PlantEntity
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain

object DataMapper {
    fun mapEntitiesToDomain(plants: List<PlantEntity>): List<PlantDomain> {
        return plants.map {
            PlantDomain(
                it.title, it.description, it.date, it.img
            )
        }
    }

    fun mapDomainToEntity(plantDomain: PlantDomain): PlantEntity {
        return PlantEntity(
            title = plantDomain.title,
            description = plantDomain.description,
            date = plantDomain.date,
            img = plantDomain.img
        )
    }
}