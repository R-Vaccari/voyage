package com.rvapp.voyage.model.api

import okhttp3.OkHttpClient
import okhttp3.Request

class GeoDBAPI {

    fun requestEntity() {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://wft-geo-db.p.rapidapi.com/v1/geo/cities?countryIds=PT&minPopulation=15000")
                .get()
                .addHeader("x-rapidapi-key", "73a22a72d3msh9a0f3e37accb5abp1b198ajsn7bdc106de086")
                .addHeader("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .build()

        val response = client.newCall(request).execute()
        val data: String = response.body!!.string()
        response.close()
    }
}