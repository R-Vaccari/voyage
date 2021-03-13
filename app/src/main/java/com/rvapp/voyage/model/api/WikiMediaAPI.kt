package com.rvapp.voyage.model.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaData
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaDeserializer
import com.rvapp.voyage.util.HashHelper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

class WikiMediaAPI {
    private val deserializer = WikiMediaDeserializer()
    private val gson: Gson = GsonBuilder().registerTypeAdapter(WikiMediaData::class.java, deserializer)
            .create()
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://www.wikidata.org/wiki/Special:EntityData/")
            .build()
    private val service = retrofit.create(WikiMediaService::class.java)

    fun requestEntity(entity: String): WikiMediaData {
        deserializer.wikiId = entity
        val call = service.getEntity("https://www.wikidata.org/wiki/Special:EntityData/$entity.json")
        val data = call.execute().body()
        return WikiMediaData(data!!.cityDescription, setPhotoUrl(data.cityPhoto), data.population)
    }

    private fun setPhotoUrl(cityPhoto: String): String {
        if (cityPhoto != "none") {
            val hash = HashHelper.md5(cityPhoto)
            val a = hash[0]
            val b = hash[1]
            return "https://upload.wikimedia.org/wikipedia/commons/$a/$a$b/${cityPhoto.replace(" ", "_")}"
        }
        return "none"
    }

    interface WikiMediaService {
        @GET fun getEntity(@Url url: String): Call<WikiMediaData>
    }
}