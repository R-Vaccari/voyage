package com.rvapp.voyage.model.entities

data class Location(val name: String,
                    val id: String,
                    val category: String,
                    val rank: Int,
                    val tags: Array<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (name != other.name) return false
        if (id != other.id) return false
        if (category != other.category) return false
        if (rank != other.rank) return false
        if (!tags.contentEquals(other.tags)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}