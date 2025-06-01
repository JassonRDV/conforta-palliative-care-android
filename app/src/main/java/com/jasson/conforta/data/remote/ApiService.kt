package com.jasson.conforta.data.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPalliativeCareData(@Url url: String): PaliativeCareResponse
}