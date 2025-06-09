@file:Suppress("DEPRECATED_ANNOTATION")

package com.beginning.uappam.api.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@Parcelize
data class Plant(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("plant_name")
    val plantName: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("created_at")
    val createdAt: Timestamp?,

    @SerializedName("updated_at")
    val updatedAt: Timestamp?
) : Parcelable
