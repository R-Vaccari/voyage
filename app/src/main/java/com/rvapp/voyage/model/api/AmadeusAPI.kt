package com.rvapp.voyage.model.api

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.rvapp.voyage.model.api.deserializers.amadeus.LocationDeserializer
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.model.entities.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmadeusAPI(val amadeus: Amadeus) {

    suspend fun getPOIs(city: City): List<com.amadeus.android.domain.resources.Location>? {
        return when (val result = amadeus.referenceData.locations.pointsOfInterest.get(41.397158,
            2.160873, 1, 10, 0)) {
            is ApiResult.Success -> result.data
            else -> null
        }
    }

    suspend fun getPOIsRaw(city: City): LinkedHashSet<Location> {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val response = amadeus.get("https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=51.509865&longitude=-0.118092&radius=1&page%5Blimit%5D=10&page%5Boffset%5D=0")
            LocationDeserializer().deserialize(Gson().fromJson(response, JsonElement::class.java),null, null)
        }
    }

}