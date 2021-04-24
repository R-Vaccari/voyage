package com.rvapp.voyage.model.api

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.domain.resources.Location
import com.rvapp.voyage.model.entities.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmadeusAPI(val amadeus: Amadeus) {

    suspend fun getPOIs(city: City): List<Location>? {
        return when (val result = amadeus.referenceData.locations.pointsOfInterest.get(41.397158,
            2.160873, 1, 10, 0)) {
            is ApiResult.Success -> result.data
            else -> null
        }
    }

    suspend fun getPOIsDirectly(city: City): String? {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            amadeus.get("https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=51.509865&longitude=-0.118092&radius=1&page%5Blimit%5D=10&page%5Boffset%5D=0")
        }
    }

}