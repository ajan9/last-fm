package com.example.myapplication.network

import android.icu.text.IDNA
import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.Query

interface ApiModel {

    fun getTopTracks(callback: RequestCompleteListener<TopTrackResponse>)
    fun getTopArtists(callback: RequestCompleteListener<TopArtistsResponse>)
    fun getSearch(artist: String, callback: RequestCompleteListener<SearchResponse>)
    fun getArtistTopTracks(artist: String, callback: RequestCompleteListener<ArtistTracksResponse>)
    fun getInfo(artist: String, callback: RequestCompleteListener<InfoResponse>)


}