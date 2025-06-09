package com.beginning.uappam.api

import com.beginning.uappam.api.data.PlantAdd
import com.beginning.uappam.api.data.PlantStatus
import com.beginning.uappam.api.data.PlantGet
import com.beginning.uappam.api.data.PlantGetAll
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlantAPI {
    @GET("all")
    suspend fun getAllPlants(): PlantGetAll

    @GET("{id}")
    suspend fun getOnePlant(id: Int): PlantGet

    @POST("new")
    suspend fun addPlant(@Body newPlant: PlantAdd) : PlantStatus

    @DELETE("{plant_name}")
    suspend fun deletePlant(@Path("plant_name") plantName: String)

    @PUT("{plant_name}")
    suspend fun updatePlant(@Path("plant_name") plantName: String, @Body updatedPlant: PlantAdd) : PlantStatus


}