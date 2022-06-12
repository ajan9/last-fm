package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.models.Track


class TrackAdapter(
    val list: MutableList<Track>
) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class  ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView
        val artist: TextView
        val listeners: TextView
        val playCount: TextView
        val image: ImageView

        init {
            name = view.findViewById(R.id.name)
            artist = view.findViewById(R.id.artist)
            listeners = view.findViewById(R.id.listeners)
            playCount = view.findViewById(R.id.playcount)
            image = view.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.track_card, parent,false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val track=list[position]
        val i = track.image.size
        viewHolder.name.text=track.name
        viewHolder.artist.text=track.artist.name
        viewHolder.listeners.text=track.listeners
        viewHolder.playCount.text=track.playcount
        Glide.with(viewHolder.image.context).load(track.image[i-1].text).into(viewHolder.image)

    }

    override fun getItemCount(): Int {
        return  list.size
    }
}