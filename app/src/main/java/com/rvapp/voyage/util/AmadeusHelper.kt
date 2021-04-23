package com.rvapp.voyage.util

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.domain.resources.Location
import com.rvapp.voyage.model.entities.City

class AmadeusHelper(val amadeus: Amadeus) {

    suspend fun getPOIs(city: City): List<Location>? {
        return when (val result = amadeus.referenceData.locations.pointsOfInterest.get(city.latitude,
            city.longitude, 1, 10, 0)) {
            is ApiResult.Success -> result.data
            else -> null
        }
    }
}