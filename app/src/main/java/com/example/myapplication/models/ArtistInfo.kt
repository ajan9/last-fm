package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class ArtistInfo(
    @SerializedName("name") var name: String,
    @SerializedName("mbid") var mbid: String,
    @SerializedName("url") var url: String,
    @SerializedName("image") var image: ArrayList<Image>,
    @SerializedName("ontour") var ontour: String,
    @SerializedName("stats") var stats: Stats,
    @SerializedName("bio") var bio: Bio
)