package com.joeldev.rickandmortywiki

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/shows/216/seasons")
    suspend fun getSeasons(): List<Season>

    @GET("/seasons/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id: Int): List<Episode>

}