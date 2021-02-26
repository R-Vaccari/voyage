package com.rvapp.voyage.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class City(val geonameid: Int?,
                val name: String?,
                val latitude: String?,
                val longitude: String?,
                val population: Int?,
                val timezone: String?,
                ) {

    override fun toString(): String {
        return "[$name, $population]"
    }
}