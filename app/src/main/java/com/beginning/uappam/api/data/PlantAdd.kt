package com.beginning.uappam.api.data

import com.google.gson.annotations.SerializedName

data class PlantAdd(
    @SerializedName("plant_name")
    val plant_name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: String
)
