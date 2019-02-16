package com.example.albumslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.Log.d

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.albumslist.Album



class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val api = retrofit.create(APIPhoto::class.java)

       // val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)

        val clickID = intent.getIntExtra(AlbumsAdapter.ViewHolder.Album_ID, 1)

        api.getPhotos(clickID).enqueue(object : Callback<List<Photo>> {

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                showData(response.body()!!)

            }


            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                d("vova", "Fail")
            }
        })

        //  val albums = mutableListOf<Album>()
        //  for(i in 0..100){
        //     albums.add(Album(1,1,"title"))
        // }


    }
    private fun showData(photos: List<Photo>) {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AlbumActivity)
            adapter = PhotoAdapter(photos)

        }
    }
}
