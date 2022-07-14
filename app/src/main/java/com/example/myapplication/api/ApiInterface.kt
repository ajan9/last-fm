package com.example.myapplication.api

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/2.0")
    fun getTopArtists(
        @Query("method") method:String="chart.gettopartists",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<TopArtistsResponse>

    @GET("/2.0")
    fun getTopTracks(
        @Query("method") method:String="chart.gettoptracks",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<TopTrackResponse>

    @GET("/2.0")
    fun getSearch(
        @Query("artist") artist:String,
        @Query("method") method:String="artist.search",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<SearchResponse>

    @GET("/2.0")
    fun getArtistTopTracks(
        @Query("artist") artist:String,
        @Query("method") method:String="artist.gettoptracks",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<ArtistTracksResponse>

    @GET("/2.0")
    fun getInfo(
        @Query("artist") artist:String,
        @Query("method") method:String="artist.getinfo",
        @Query("api_key") apiKey:String="3e5276651fae7512493e4cb28d830fbb",
        @Query("format") format:String="json"
    ): Call<InfoResponse>

}