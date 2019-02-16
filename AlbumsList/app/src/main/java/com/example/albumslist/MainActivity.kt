package com.example.albumslist

import android.os.Bundle

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val api = retrofit.create(APIService::class.java)

        api.getAllAlbums().enqueue(object : Callback<List<Album>> {

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                showData(response.body()!!)
                d("vova","onResponse: ${response.body()!![0].id}")
            }


            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                d("vova", "Fail")
            }
        })

      //  val albums = mutableListOf<Album>()
      //  for(i in 0..100){
       //     albums.add(Album(1,1,"title"))
       // }



    }
    private fun showData(albums: List<Album>) {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = AlbumsAdapter(albums)

        }
    }
}
