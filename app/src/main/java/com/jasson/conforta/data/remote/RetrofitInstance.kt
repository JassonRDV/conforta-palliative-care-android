package com.jasson.conforta.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL =
        "https://drive.google.com/file/d/1PpZVjF-myi0hPIguG_LBg9_G5_X1bg15/view?usp=drive_link"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val httpClient = OkHttpClient.Builder().apply {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        addInterceptor(logging)
    }.build()

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}