package com.rvapp.voyage.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.properties.Delegates

data class City(@SerializedName("wikiDataId") val wikiDataId: String,
                @SerializedName("name") val name: String,
                @SerializedName("latitude") val latitude: Double,
                @SerializedName("longitude") val longitude: Double): Serializable {

    lateinit var description: String
    lateinit var photo_url: String
    var population by Delegates.notNull<Int>()
    var elevation by Delegates.notNull<Int>()

    override fun toString(): String {
        return "[$name]"
    }
}