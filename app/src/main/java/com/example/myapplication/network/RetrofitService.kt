package com.example.myapplication.network

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/2.0")
    fun getTopTracks(): Call<TopTrackResponse>

    @GET("/2.0")
    fun getTopArtists(): Call<TopArtistsResponse>

    @GET("/2.0")
    fun getSearch(album_id:String): Call<SearchResponse>

    @GET("/2.0")
    fun getArtistTopTracks(album_id:String): Call<ArtistTracksResponse>

    @GET("/2.0")
    fun getInfo(album_id:String): Call<InfoResponse>

    companion object{
        var BASE_URL="http://ws.audioscrobbler.com"
        fun create() : RetrofitService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}