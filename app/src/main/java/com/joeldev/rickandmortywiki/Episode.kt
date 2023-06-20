package com.joeldev.rickandmortywiki

import com.google.gson.annotations.SerializedName


data class Episode(
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: Int,
    @SerializedName("season") val season: Int,
    @SerializedName("summary") val description: String,
    @SerializedName("airtime") val duration: String,
    @SerializedName("image") val image:Image,
    @SerializedName("rating") val rate: Rate
)

data class Image(@SerializedName("original") val url:String)

data class Rate(@SerializedName("average") val rating: Double)
