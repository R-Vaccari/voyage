package com.rvapp.voyage.model.api.deserializers.wikimedia

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaData
import java.lang.reflect.Type

class WikiMediaDeserializer: JsonDeserializer<WikiMediaData> {
    lateinit var wikiId: String

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): WikiMediaData {
        val entity: JsonObject = json!!.asJsonObject
                .getAsJsonObject("entities")
                .getAsJsonObject(wikiId)

        val description: String = entity
                .getAsJsonObject("descriptions")
                .getAsJsonObject("en")
                .get("value").asString

        var population: Int
        try {
            population = entity
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P1082")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .getAsJsonObject("value")
                    .get("amount").asString.removePrefix("+")
                    .toInt()
        } catch (e: NullPointerException) { population = -100 }

        var p948: String
        try {
            p948 = entity
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
            p18 = entity
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P18")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .get("value").asString
                    .replace(" ", "_")
        }  catch (e: NullPointerException) { p18 = "none" }

        var elevation: Int
        try {
            elevation = entity
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