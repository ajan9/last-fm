package com.example.myapplication.models

import com.google.gson.annotations.SerializedName


data class TopArtists (
    @SerializedName("artist")  val artists: List<Artist>
)