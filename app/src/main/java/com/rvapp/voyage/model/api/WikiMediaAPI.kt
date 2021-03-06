package com.rvapp.voyage.model.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request

class WikiMediaAPI {

    fun requestEntity(entity: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.wikidata.org/wiki/Special:EntityData/$entity.json")
            .get()
            .build()

        val response = client.newCall(request).execute()
        val data: String = response.body!!.string()
        response.close()
        return data
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
}