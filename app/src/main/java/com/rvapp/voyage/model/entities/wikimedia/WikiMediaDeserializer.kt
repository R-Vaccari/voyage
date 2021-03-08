package com.rvapp.voyage.model.entities.wikimedia

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class WikiMediaDeserializer: JsonDeserializer<WikiMediaData> {
    lateinit var wikiId: String

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): WikiMediaData {
        val jsonObject: JsonObject = json!!.asJsonObject
        val description: String = jsonObject.getAsJsonObject("entities")
                .getAsJsonObject(wikiId)
                .getAsJsonObject("en")
                .get("value").asString

        val cityPhoto: String = jsonObject.getAsJsonObject("entities")
                .getAsJsonObject(wikiId)
                .getAsJsonObject("claims")
                .getAsJsonArray("P948")
                .get(0).asJsonObject
                .getAsJsonObject("mainsnak")
                .getAsJsonObject("datavalue")
                .get("value").asString
                .replace(" ", "_")

        return WikiMediaData(description, cityPhoto)
    }
}