package com.rvapp.voyage.model.api

import com.rvapp.voyage.model.entities.City
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class CountriesCitiesAPI {

    companion object {
        private const val key = "73a22a72d3msh9a0f3e37accb5abp1b198ajsn7bdc106de086"
        private const val header_key = "x-rapidapi-key"
        private const val host = "countries-cities.p.rapidapi.com"
        private const val header_host = "x-rapidapi-host"

        private fun makeRequestByCountry(countryCode: String): String {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://countries-cities.p.rapidapi.com/location/country/$countryCode/city/list")
                .get()
                .addHeader(header_key, key)
                .addHeader(header_host, host)
                .build()

            val response = client.newCall(request).execute()
            val result = response.body!!.string()
            response.close()
            return result
        }

        fun getCitiesFromCountryCode(countryCode: String): MutableList<City> {
            val cities = mutableListOf<City>()
            val array = JSONObject(makeRequestByCountry(countryCode)).getJSONArray("cities")
            for (i in 0 until array.length()) {
                val jsonObject = array.getJSONObject(i)
                val city = City(
                    jsonObject.optInt("geonameid"),
                    jsonObject.optString("name"),
                    jsonObject.optString("latitude"),
                    jsonObject.optString("longitude"),
                    jsonObject.optInt("population"),
                    jsonObject.optString("timezone"),
                null)
                cities.add(city)
            }
            return cities
        }
    }
}