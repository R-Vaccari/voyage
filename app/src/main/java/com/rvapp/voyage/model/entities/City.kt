package com.rvapp.voyage.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(@SerializedName("wikiDataId") val wikiDataId: String,
                @SerializedName("name") val name: String,
                @SerializedName("latitude") val latitude: Double,
                @SerializedName("longitude") val longitude: Double): Serializable {

    lateinit var description: String
    lateinit var photo_url: String

    override fun toString(): String {
        return "[$name]"
    }
}