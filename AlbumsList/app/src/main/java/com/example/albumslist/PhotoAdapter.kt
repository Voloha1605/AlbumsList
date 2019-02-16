package com.example.albumslist

import android.content.Intent
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*
import kotlinx.android.synthetic.main.image_row.view.*


class PhotoAdapter(private val photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // d("album","title? ${.title}")
        val photo = photos [position]

        holder.title.text = photo.title

       val smallImage = holder.itemView.image

        Picasso.get().load(photo.thumbnailUrl).into(smallImage)

        holder.photo = photo
      //  val SmallImage = holder?.view?.imageView_channel_profile
      //  Picasso.with(holder?.view?.context).load(video.channel.profileImageUrl).into(channelProfileImageView)
    }

    class ViewHolder(itemView: View, var photo: Photo? = null ) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.image_title

        companion object {
            val Photo_Url = "Photo_Url"
        }


        init {
            itemView.setOnClickListener{

                val intent = Intent(itemView.context,ImageActivity::class.java)

                intent.putExtra(Photo_Url,photo?.url)

                itemView.context.startActivity(intent)
            }
        }
    }
}