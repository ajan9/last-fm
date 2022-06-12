package com.example.myapplication.api

import com.example.myapplication.models.TopArtistsResponse
import com.example.myapplication.models.TopTrackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/2.0")
    fun getTopArtists(
        @Query("method") method:String="chart.getTopArtists",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<TopArtistsResponse>

    @GET("/2.0")
    fun getTopTracks(
        @Query("method") method:String="chart.getTopTracks",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<TopTrackResponse>

}