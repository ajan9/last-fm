package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.adapters.ArtistDetailAdapter
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.models.ArtistInfo
import com.example.myapplication.models.ArtistTracksResponse
import com.example.myapplication.models.InfoResponse
import com.example.myapplication.models.Track
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call


class ArtistDetail : Fragment() {

    private lateinit var artist_name: String
    private var recyclerView: RecyclerView? = null
    private var name: TextView? = null
    private var artist_image: CircleImageView? = null
    private var bio: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            artist_name = requireArguments().getString("artist_name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tracks: MutableList<Track> = mutableListOf()
        var artist: ArtistInfo
        var artistdetailAdapter: ArtistDetailAdapter = ArtistDetailAdapter(tracks)

        recyclerView = view.findViewById(R.id.recycleView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = artistdetailAdapter

        name = view.findViewById(R.id.name)
        artist_image = view.findViewById(R.id.artist_image)
        bio = view.findViewById(R.id.bio)

        val response = RetrofitInstance.api.getArtistTopTracks(artist_name)
        response.enqueue(
            object : retrofit2.Callback<ArtistTracksResponse> {
                override fun onResponse(
                    call: retrofit2.Call<ArtistTracksResponse>,
                    response: retrofit2.Response<ArtistTracksResponse>
                ) {
                    if (response.body() != null) {
                        val topTrackResponse = (response.body()!!)
                        tracks.addAll(topTrackResponse.toptracks.track)
                        artistdetailAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ArtistTracksResponse>, t: Throwable) {
                    Log.d("MSG: ", t.toString())
                }

            },
        )

        val response_artist = RetrofitInstance.api.getInfo(artist_name)
        response_artist.enqueue(
            object : retrofit2.Callback<InfoResponse> {
                override fun onResponse(
                    call: retrofit2.Call<InfoResponse>,
                    response_artist: retrofit2.Response<InfoResponse>
                ) {
                    if (response_artist.body() != null) {
                        val infoResponse = (response_artist.body()!!)
                        artist = infoResponse.artist
                        init(artist)
                    }
                }

                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                    Log.d("MSG: ", t.toString())
                }
            },
        )

    }

    fun init(artistInfo: ArtistInfo){
        name!!.text = artistInfo.name
        bio!!.text = artistInfo.bio.summary
        Glide.with(artist_image!!.context).load(artistInfo.image[0].text).into(artist_image!!)
    }
}