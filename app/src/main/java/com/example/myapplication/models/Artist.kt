package com.example.myapplication.models

import com.google.gson.annotations.SerializedName;

data class Artist(
    @SerializedName("mbid") val mbid: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<Image>,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("playcount") val playcount: String
)
