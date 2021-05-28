package com.mfahmi.mymedicineplantidentification.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.mymedicineplantidentification.data.DataDummy
import com.mfahmi.mymedicineplantidentification.data.models.Plants

class HomeViewModel : ViewModel() {
    val plantsDummy: LiveData<List<Plants>> = DataDummy.getPlantsDummy()
}