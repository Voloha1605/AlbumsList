package com.example.albumslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.util.Log.d

import kotlinx.android.synthetic.main.album_row.view.*

class AlbumsAdapter(private val albums: List<Album>) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // d("album","title? ${.title}")
        val album = albums [position]

        holder.title.text = album.title

        holder.album = album


    }

    class ViewHolder(itemView: View, var album: Album? = null) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.title
        companion object {
            val Album_ID = "Album_ID"
        }


        init {
            itemView.setOnClickListener{

                val intent = Intent(itemView.context,AlbumActivity::class.java)
                intent.putExtra(Album_ID,album?.id)

                itemView.context.startActivity(intent)
            }
        }
    }
}