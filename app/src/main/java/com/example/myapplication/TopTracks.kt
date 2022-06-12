package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ArtistAdapter
import com.example.myapplication.adapters.TrackAdapter
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.models.Artist
import com.example.myapplication.models.TopArtistsResponse
import com.example.myapplication.models.TopTrackResponse
import com.example.myapplication.models.Track
import retrofit2.Call


class TopTracks : Fragment() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_tracks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tracks: MutableList<Track> = mutableListOf()
        var trackAdapter: TrackAdapter = TrackAdapter(tracks)

        recyclerView = view.findViewById(R.id.recycleView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = trackAdapter

        val response = RetrofitInstance.api.getTopTracks()
        response.enqueue(
            object : retrofit2.Callback<TopTrackResponse> {
                override fun onResponse(
                    call: retrofit2.Call<TopTrackResponse>,
                    response: retrofit2.Response<TopTrackResponse>
                ) {
                    if (response.body() != null) {
                        val topTrackResponse = (response.body())!!
                        tracks.addAll(topTrackResponse.tracks.track)
                        trackAdapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<TopTrackResponse>, t: Throwable) {
                }
            },
        )
    }
}