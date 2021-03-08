package com.rvapp.voyage.model.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaData
import com.rvapp.voyage.model.entities.wikimedia.WikiMediaDeserializer
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

class WikiMediaAPI {
    private val gson: Gson = GsonBuilder().registerTypeAdapter(WikiMediaData::class.java, WikiMediaDeserializer())
            .create()
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://www.wikidata.org/wiki/Special:EntityData/")
            .build()
    private val service = retrofit.create(WikiMediaService::class.java)

    fun requestEntity(entity: String): WikiMediaData? {
        val call = service.getEntity("https://www.wikidata.org/wiki/Special:EntityData/$entity.json")
        val response = call.execute()
        return response.body()
    }

    fun requestEntityProperty(): String {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://www.wikidata.org/w/api.php?action=wbgetclaims&property=P18&entity=Qxxx")
                .get()
                .build()

        val response = client.newCall(request).execute()
        val data: String = response.body!!.string()
        response.close()
        return data
    }

    fun requestPicture(a: Char, b: Char, image: String): Bitmap {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://upload.wikimedia.org/wikipedia/commons/$a/$a$b/$image")
                .get()
                .build()

        val response = client.newCall(request).execute()
        val bitmap = response.body!!.bytes()
        response.close()
        return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.size)
    }

    interface WikiMediaService {

        @GET
        fun getEntity(@Url url: String): Call<WikiMediaData>

        @GET("https://upload.wikimedia.org/wikipedia/commons/")
        fun getPhoto(@Url url: String, @Path("a") a: String, @Path("ab") ab: String, @Path("image") image: String)
    }
}