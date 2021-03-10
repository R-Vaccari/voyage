package com.rvapp.voyage.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GeoCitiesData(@SerializedName("data") val cities: LinkedHashSet<City>): Serializable {
}