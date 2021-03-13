package com.rvapp.voyage.model.entities.wikimedia

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.NullPointerException
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

        var population: String
        try {
            population = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("1082")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .getAsJsonObject("value")
                    .get("amound").asString.removePrefix("+")
        } catch (e: NullPointerException) {
            population = "none"
        }

        var p948: String
        try {
            p948 = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P948")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .get("value").asString
                    .replace(" ", "_")
        } catch (e: NullPointerException) {
            p948 = "none"
        }

        var p18: String
        try {
            p18 = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P948")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .get("value").asString
                    .replace(" ", "_")
        }  catch (e: NullPointerException) {
            p18 = "none"
        }

        var cityPhoto: String = "none"
        if (p948 != "none") cityPhoto = p948
        else if (p18 != "none") cityPhoto = p18
        return WikiMediaData(description, cityPhoto, population.toInt())
    }
}