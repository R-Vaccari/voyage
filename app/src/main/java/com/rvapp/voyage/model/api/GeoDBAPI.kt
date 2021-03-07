package com.rvapp.voyage.model.api

import com.rvapp.voyage.model.entities.GeoCitiesData
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class GeoDBAPI {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://wft-geo-db.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val service = retrofit.create(GeoDBService::class.java)

    fun getCities(countryIds: String, minPop: String) {
        val call = service.getCities(countryIds, minPop)
        val response = call.execute()
        val body = response.body()
    }

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

    interface GeoDBService {

        @Headers(
            "x-rapidapi-key: 73a22a72d3msh9a0f3e37accb5abp1b198ajsn7bdc106de086",
            "x-rapidapi-host: wft-geo-db.p.rapidapi.com"
        )
        @GET("v1/geo/cities")
        fun getCities(@Query("countryIds") countryIds: String, @Query("minPopulation") minPop: String): Call<GeoCitiesData>
    }
}