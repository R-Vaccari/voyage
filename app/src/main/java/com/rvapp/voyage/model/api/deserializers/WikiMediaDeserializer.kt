package com.rvapp.voyage.model.api.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaData
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

        var population: Int
        try {
            population = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("1082")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .getAsJsonObject("value")
                    .get("amound").asString.removePrefix("+")
                    .toInt()
        } catch (e: NullPointerException) { population = 0 }

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
        } catch (e: NullPointerException) { p948 = "none" }

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
        }  catch (e: NullPointerException) { p18 = "none" }

        var elevation: Int
        try {
            elevation = entityObject
                .getAsJsonObject("claims")
                .getAsJsonArray("P3044")
                .get(0).asJsonObject
                .getAsJsonObject("mainsnak")
                .getAsJsonObject("datavalue")
                .getAsJsonObject("value")
                .get("amount").asInt
        } catch (e: NullPointerException) { elevation = -100 }

        var cityPhoto = "none"
        if (p948 != "none") cityPhoto = p948
        else if (p18 != "none") cityPhoto = p18
        return WikiMediaData(description, cityPhoto, population, elevation)
    }
}