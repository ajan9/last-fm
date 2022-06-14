package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ArtistAdapter
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.models.Artist
import com.example.myapplication.models.TopArtistsResponse
import retrofit2.Call


class TopArtists : Fragment() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var artist: MutableList<Artist> = mutableListOf()
        var artistAdapter: ArtistAdapter = ArtistAdapter(artist)

        recyclerView = view.findViewById(R.id.recycleView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = artistAdapter

        val response = RetrofitInstance.api.getTopArtists()
        response.enqueue(
            object : retrofit2.Callback<TopArtistsResponse> {
                override fun onResponse(
                    call: retrofit2.Call<TopArtistsResponse>,
                    response: retrofit2.Response<TopArtistsResponse>
                ) {
                    if (response.body() != null) {
                        val topArtistResponse = (response.body())!!
                        artist.addAll(topArtistResponse.topartists.artists)
                        artistAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<TopArtistsResponse>, t: Throwable) {
                    Toast.makeText(context, "Failed $t", Toast.LENGTH_LONG).show()
                }

            },
        )
    }
}