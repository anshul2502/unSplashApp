package com.images.unsplashsampleapp.ViewModel

import androidx.lifecycle.*
import com.app.weatherreport.api.Resource
import com.images.unsplashsampleapp.data.model.DataModel
import com.images.unsplashsampleapp.data.repository.DataRepository
class ImageViewModel(): ViewModel() {

    var data:LiveData<Resource<List<DataModel>>>?=null

    fun getSplashImages(){
        data = DataRepository().getWeatherData()
    }

}