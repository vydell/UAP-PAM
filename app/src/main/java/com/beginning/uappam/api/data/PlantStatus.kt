package com.beginning.uappam.api.data

import com.google.gson.annotations.SerializedName

data class PlantStatus(
    @SerializedName("status")
    val status: String?,

    @SerializedName("message")
    val message: String?
)
