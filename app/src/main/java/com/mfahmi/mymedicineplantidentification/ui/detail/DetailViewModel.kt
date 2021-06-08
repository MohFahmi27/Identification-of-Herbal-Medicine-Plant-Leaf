package com.mfahmi.mymedicineplantidentification.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.mymedicineplantidentification.data.DataDummy
import com.mfahmi.mymedicineplantidentification.domain.models.Plants

class DetailViewModel : ViewModel() {
    val plantsDummy: LiveData<List<Plants>> = DataDummy.getPlantsDummy()
}