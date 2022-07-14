package com.example.myapplication.models

import com.google.gson.annotations.SerializedName;

data class ArtistSearch(
    @SerializedName("mbid") val mbid: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: Image,
)
