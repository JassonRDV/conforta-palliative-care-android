package com.jasson.obesidade.data.remote

import com.jasson.obesidade.domain.model.PaliativeCareResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("uc")
    suspend fun getJsonDataFromGoogleDrive(
        @Query("id") fileId: String,
        @Query("export") exportType: String = "download",
    ): Response<PaliativeCareResponse>
}