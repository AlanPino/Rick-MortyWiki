package com.joeldev.rickandmortywiki

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/seasons")
    suspend fun getSeasons():List<Season>

    @GET("/seasons/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id:Int):List<Episode>
}