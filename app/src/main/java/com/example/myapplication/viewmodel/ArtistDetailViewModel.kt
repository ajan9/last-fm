package com.example.myapplication.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.*
import com.example.myapplication.network.ApiModel
import com.example.myapplication.network.RequestCompleteListener

class ArtistDetailViewModel(application: Application) : AndroidViewModel(application) {
    var tracksList = MutableLiveData<List<Track>>()
    var info = MutableLiveData<ArtistInfo>()

    private var sharedPreferences = application.getSharedPreferences("MyPref", Context.MODE_PRIVATE)!!

    fun getArtistTopTracksList(model: ApiModel){
        val artist = sharedPreferences.getLong("artist", 0)
        model.getArtistTopTracks(artist.toString(), object: RequestCompleteListener<ArtistTracksResponse> {
            override fun onRequestSuccess(tracks: ArtistTracksResponse) {
                tracksList.postValue(tracks.toptracks.track)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("error", "retrofit failed")
            }
        })
    }

    fun getInfoList(model: ApiModel){
        val artist = sharedPreferences.getLong("artist", 0)
        model.getInfo(artist.toString(), object: RequestCompleteListener<InfoResponse> {
            override fun onRequestSuccess(infoRes: InfoResponse) {
                info.postValue(infoRes.artist)
            }
            override fun onRequestFailed(errorMessage: String) {
                Log.d("error", "retrofit failed")
            }
        })
    }
}