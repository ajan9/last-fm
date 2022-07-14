package com.example.myapplication.models

import com.google.gson.annotations.SerializedName


data class Bio (

    @SerializedName("published" ) var published : String? = null,
    @SerializedName("summary"   ) var summary   : String? = null,
    @SerializedName("content"   ) var content   : String? = null

)
