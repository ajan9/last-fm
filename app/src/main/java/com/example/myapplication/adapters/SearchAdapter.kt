package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.SearchDirections
import com.example.myapplication.models.Artist

class SearchAdapter(
    val list: MutableList<Artist>
): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val image: ImageView

        init {
            name = view.findViewById(R.id.name)
            image = view.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.artist_card, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val artist=list[position]
        viewHolder.name.text = artist.name
        val i = artist.image.size
        Glide.with(viewHolder.image.context).load(artist.image[i-1].text).into(viewHolder.image)

        viewHolder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(SearchDirections.actionSearchToArtistDetail(artist.name))
        }
    }


    override fun getItemCount(): Int {
        return  list.size
    }

}
