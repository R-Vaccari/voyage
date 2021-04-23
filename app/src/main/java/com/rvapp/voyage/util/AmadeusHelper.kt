package com.rvapp.voyage.util

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.referenceData.Location
import com.rvapp.voyage.model.entities.City

class AmadeusHelper(val amadeus: Amadeus) {

    suspend fun getPOIs(city: City): List<Location>? {
        return when (val result = amadeus.referenceData.locations.pointsOfInterest.get(51.509865,
            -0.118092, 1, 1, 0)) {
            is ApiResult.Success -> result.data as List<Location>?
            else -> null
        }
    }
}