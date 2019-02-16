package com.example.albumslist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIPhoto {



    @GET("/photos")
    fun getPhotos(@Query("albumId") albumId: Int) : Call<List<Photo>>

}