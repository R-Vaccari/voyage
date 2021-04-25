package com.rvapp.voyage.model.api.deserializers.amadeus

import com.google.gson.*
import com.rvapp.voyage.model.entities.Location
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaData
import java.lang.reflect.Type

class LocationDeserializer: JsonDeserializer<LinkedHashSet<Location>> {
    lateinit var wikiId: String

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LinkedHashSet<Location> {
        val array: JsonArray = json!!.asJsonObject
                .getAsJsonArray("data")
        val locations = LinkedHashSet<Location>()

        for (jsonObject: JsonElement in array) {
            val location = jsonObject.asJsonObject
            locations.add(Location(location.get("name").asString, location.get("id").asString,
            location.get("category").asString, location.get("rank").asInt, Gson().fromJson(location.get("tags"), Array<String>::class.java)))
        }
        return locations
    }
}