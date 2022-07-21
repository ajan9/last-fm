package com.example.myapplication.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.models.Artist
import com.example.myapplication.models.SearchResponse
import com.example.myapplication.models.Track
import com.example.myapplication.network.ApiModel
import com.example.myapplication.network.RequestCompleteListener

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    var artistsList = MutableLiveData<List<Artist>>()

    private var sharedPreferences = application.getSharedPreferences("MyPref", Context.MODE_PRIVATE)!!

    fun getSearchArtistList(model: ApiModel){
        val artist = sharedPreferences.getString("artist", "")
        if (artist != null) {
            model.getSearch(artist, object: RequestCompleteListener<SearchResponse> {
                override fun onRequestSuccess(artistsS: SearchResponse) {
                    artistsList.postValue(artistsS.results.artistmatches.artist)
                }
                override fun onRequestFailed(errorMessage: String) {
                    Log.d("error", "retrofit failed")
                }
            })
        }
    }
}