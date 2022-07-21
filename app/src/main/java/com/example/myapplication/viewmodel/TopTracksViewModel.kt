package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Artist
import com.example.myapplication.models.TopArtistsResponse
import com.example.myapplication.models.TopTrackResponse
import com.example.myapplication.models.Track
import com.example.myapplication.network.ApiModel
import com.example.myapplication.network.RequestCompleteListener

class TopTracksViewModel : ViewModel(){

    val topTracksLiveData = MutableLiveData<List<Track>>()

    fun getTopTracksList(model: ApiModel) {
        model.getTopTracks(object : RequestCompleteListener<TopTrackResponse> {
            override fun onRequestSuccess(tracksData: TopTrackResponse){
                topTracksLiveData.postValue(tracksData.tracks.track)
            }
            override fun onRequestFailed(errorMessage: String) {
            }
        })
    }
}