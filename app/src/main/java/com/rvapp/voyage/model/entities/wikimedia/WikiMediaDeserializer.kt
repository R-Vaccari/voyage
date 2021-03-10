package com.rvapp.voyage.model.entities.wikimedia

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class WikiMediaDeserializer: JsonDeserializer<WikiMediaData> {
    lateinit var wikiId: String

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): WikiMediaData {
        val entityObject: JsonObject = json!!.asJsonObject
                .getAsJsonObject("entities")
                .getAsJsonObject(wikiId)

        val description: String = entityObject
                .getAsJsonObject("descriptions")
                .getAsJsonObject("en")
                .get("value").asString

        val population: String = entityObject
                .getAsJsonObject("claims")
                .getAsJsonArray("1082")
                .get(0).asJsonObject
                .getAsJsonObject("mainsnak")
                .getAsJsonObject("datavalue")
                .getAsJsonObject("value")
                .get("amound").asString.removePrefix("+")

        val p948: String? = entityObject
                .getAsJsonObject("claims")
                .getAsJsonArray("P948")
                .get(0).asJsonObject
                .getAsJsonObject("mainsnak")
                .getAsJsonObject("datavalue")
                .get("value").asString
                .replace(" ", "_")

        val p18: String? = entityObject
                .getAsJsonObject("claims")
                .getAsJsonArray("P948")
                .get(0).asJsonObject
                .getAsJsonObject("mainsnak")
                .getAsJsonObject("datavalue")
                .get("value").asString
                .replace(" ", "_")

        var cityPhoto: String = "none"
        when {
            p948 != null -> cityPhoto = p948
            p18 != null -> cityPhoto = p18
        }
        return WikiMediaData(description, cityPhoto, population.toInt())
    }
}