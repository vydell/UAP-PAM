package com.beginning.uappam.api.data

import com.google.gson.annotations.SerializedName

data class PlantGetAll (
    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<Plant>?
)