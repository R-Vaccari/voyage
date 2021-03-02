package com.rvapp.voyage.model.entities

data class City(val geonameid: Int?,
                val name: String?,
                val latitude: String?,
                val longitude: String?,
                val population: Int?,
                val timezone: String?,
                val picture: String?) {

    override fun toString(): String {
        return "[$name, $population]"
    }
}