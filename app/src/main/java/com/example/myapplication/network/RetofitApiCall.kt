package com.example.myapplication.network

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitApiCall () : ApiModel {

    override fun getTopTracks(callback: RequestCompleteListener<TopTrackResponse>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<TopTrackResponse> = interfaceAPI.getTopTracks()

        call.enqueue(object : Callback<TopTrackResponse> {
            override fun onResponse(call: Call<TopTrackResponse>, response: Response<TopTrackResponse>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<TopTrackResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }

    override fun getTopArtists(callback: RequestCompleteListener<TopArtistsResponse>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<TopArtistsResponse> = interfaceAPI.getTopArtists()

        call.enqueue(object : Callback<TopArtistsResponse> {
            override fun onResponse(call: Call<TopArtistsResponse>, response: Response<TopArtistsResponse>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<TopArtistsResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }

    override fun getSearch(artist: String, callback: RequestCompleteListener<SearchResponse>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<SearchResponse> = interfaceAPI.getSearch(artist)

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }

    override fun getArtistTopTracks(artist: String, callback: RequestCompleteListener<ArtistTracksResponse>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<ArtistTracksResponse> = interfaceAPI.getArtistTopTracks(artist)

        call.enqueue(object : Callback<ArtistTracksResponse> {
            override fun onResponse(call: Call<ArtistTracksResponse>, response: Response<ArtistTracksResponse>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<ArtistTracksResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }

    override fun getInfo(artist: String, callback: RequestCompleteListener<InfoResponse>) {
        val interfaceAPI : RetrofitService = RetrofitService.create()
        val call : Call<InfoResponse> = interfaceAPI.getInfo(artist)

        call.enqueue(object : Callback<InfoResponse> {
            override fun onResponse(call: Call<InfoResponse>, response: Response<InfoResponse>) {
                if (response.isSuccessful) {
                    callback.onRequestSuccess(response.body()!!)
                }else{
                    callback.onRequestFailed(response.message())
                }
            }
            override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!)
            }
        })
    }
}