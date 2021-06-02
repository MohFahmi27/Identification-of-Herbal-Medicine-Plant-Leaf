package com.mfahmi.mymedicineplantidentification.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.mymedicineplantidentification.domain.models.Plants

object DataDummy {
    fun getPlantsDummy(): LiveData<List<Plants>> {
        val plantsData = MutableLiveData<List<Plants>>()
        plantsData.value = listOf(
            Plants(
                "Jelly Grass (Cincau)",
                "https://5.imimg.com/data5/SS/DS/MY-10247618/basil-leaves-500x500.jpg"
            ),
            Plants(
                "Caraway leaf (Jintan)",
                "https://5.imimg.com/data5/SS/DS/MY-10247618/basil-leaves-500x500.jpg"
            ),
            Plants(
                "Basil Leaf (Selasih)",
                "https://5.imimg.com/data5/SS/DS/MY-10247618/basil-leaves-500x500.jpg"
            ),
            Plants(
                "Betel Leaf (Sirih)",
                "https://5.imimg.com/data5/SS/DS/MY-10247618/basil-leaves-500x500.jpg"
            ),
            Plants(
                "Jelly Grass (Cincau)",
                "https://5.imimg.com/data5/SS/DS/MY-10247618/basil-leaves-500x500.jpg"
            )
        )
        return plantsData
    }
}