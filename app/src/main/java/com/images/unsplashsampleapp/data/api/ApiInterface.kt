package com.images.unsplashsampleapp.data.api

import androidx.viewbinding.BuildConfig
import com.images.unsplashsampleapp.data.model.DataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("/photos")
    fun getImages():Call <List<DataModel>>

    //"https://jsonplaceholder.typicode.com/"

    //Initializing Retrofit
    companion object{

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        operator fun invoke(
        ) :  ApiInterface{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                        })
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}