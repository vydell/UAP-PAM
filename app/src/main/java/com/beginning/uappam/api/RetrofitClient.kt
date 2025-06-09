package com.beginning.uappam.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseURL = "https://uappam.kuncipintu.my.id/plant/"

    val instance: PlantAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PlantAPI::class.java)
    }
}