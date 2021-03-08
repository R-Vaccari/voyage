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

        var cityPhoto: String
        try {
            cityPhoto = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P948")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .get("value").asString
                    .replace(" ", "_")
        } catch (e: NullPointerException) {
            cityPhoto = entityObject
                    .getAsJsonObject("claims")
                    .getAsJsonArray("P18")
                    .get(0).asJsonObject
                    .getAsJsonObject("mainsnak")
                    .getAsJsonObject("datavalue")
                    .get("value").asString
                    .replace(" ", "_")
        }
        return WikiMediaData(description, cityPhoto)
    }
}