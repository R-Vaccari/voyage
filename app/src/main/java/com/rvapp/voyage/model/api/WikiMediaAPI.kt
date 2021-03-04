package com.rvapp.voyage.model.api

import okhttp3.OkHttpClient
import okhttp3.Request

class WikiMediaAPI {

    fun requestEntity() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.wikidata.org/wiki/Special:EntityData/Q597.json")
            .get()
            .build()

        val response = client.newCall(request).execute()
        val data: String = response.body!!.string()
        response.close()
    }
}