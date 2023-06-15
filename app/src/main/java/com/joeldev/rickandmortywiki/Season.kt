package com.joeldev.rickandmortywiki

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("id") val id: Int,
    @SerializedName("number") val number: Int
)
