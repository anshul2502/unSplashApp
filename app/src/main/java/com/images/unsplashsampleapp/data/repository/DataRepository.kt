package com.images.unsplashsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.weatherreport.api.Resource
import com.images.unsplashsampleapp.data.api.ApiInterface
import com.images.unsplashsampleapp.data.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataRepository(){

    //Api call to get data from the server
    fun getWeatherData(): LiveData<Resource<List<DataModel>>> {

        val  weatherResponse = MutableLiveData<Resource<List<DataModel>>>()

        ApiInterface().getImages().enqueue(object:Callback<List<DataModel>>{
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                weatherResponse.value = Resource.error(null,t.message.toString())
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                if(response.isSuccessful){
                    //val res:DataModel = response.body()!!
                    weatherResponse.value = Resource.success(response.body()!!)
                }else{
                    weatherResponse.value = Resource.error(null,"Error")
                }
            }
        })
        return weatherResponse
    }


}