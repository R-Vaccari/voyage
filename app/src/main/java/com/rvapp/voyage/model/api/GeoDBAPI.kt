package com.rvapp.voyage.model.api

import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.model.entities.GeoCitiesData
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

    fun getCities(countryIds: String, minPop: String): List<City> {
        val call = service.getCities(countryIds, minPop)
        val response = call.execute()
        return response.body()!!.cities
    }

    interface GeoDBService {
        @Headers("x-rapidapi-key: 73a22a72d3msh9a0f3e37accb5abp1b198ajsn7bdc106de086",
            "x-rapidapi-host: wft-geo-db.p.rapidapi.com")
        @GET("v1/geo/cities")
        fun getCities(@Query("countryIds") countryIds: String, @Query("minPopulation") minPop: String): Call<GeoCitiesData>
    }
}