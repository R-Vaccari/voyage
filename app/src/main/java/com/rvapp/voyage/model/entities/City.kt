package com.rvapp.voyage.model.entities

data class City(val wikiDataId: String,
                val name: String,
                val latitude: Double,
                val longitude: Double,
                val population: Int,
                val timezone: String) {

    lateinit var description: String
    lateinit var photo_url: String

    override fun toString(): String {
        return "[$name, $population]"
    }
}