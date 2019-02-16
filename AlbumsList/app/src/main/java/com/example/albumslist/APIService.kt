package com.example.albumslist

import retrofit2.Call
import retrofit2.http.GET



interface APIService {

    @GET("/albums")
    fun getAllAlbums(): Call<List<Album>>


}