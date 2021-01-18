package com.images.unsplashsampleapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(

/*{
    "albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
}*/


    @Expose
    @SerializedName("albumId")
    val albumId:Int,
    @Expose
    @SerializedName("id")
    val id:Int,
    @Expose
    @SerializedName("title")
    val title:String,
    @Expose
    @SerializedName("url")
    val url:String,
    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl:String
) : Parcelable