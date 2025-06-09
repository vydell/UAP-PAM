package com.beginning.uappam.api.data

import com.google.gson.annotations.SerializedName

data class PlantGet(
    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: Plant?
)
