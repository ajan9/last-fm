package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Artist
import com.example.myapplication.models.Artists
import com.example.myapplication.models.TopArtistsResponse
import com.example.myapplication.models.TopTrackResponse
import com.example.myapplication.network.ApiModel
import com.example.myapplication.network.RequestCompleteListener

class TopArtistViewModel : ViewModel(){

    val topArtistLiveData = MutableLiveData<List<Artist>>()

    fun getTopArtistsList(model: ApiModel) {
        model.getTopArtists(object : RequestCompleteListener<TopArtistsResponse> {
            override fun onRequestSuccess(artists: TopArtistsResponse){
                topArtistLiveData.postValue(artists.artists.artist)
            }
            override fun onRequestFailed(errorMessage: String) {
            }
        })
    }
}