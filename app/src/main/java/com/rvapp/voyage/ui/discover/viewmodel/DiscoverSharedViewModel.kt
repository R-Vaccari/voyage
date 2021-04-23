package com.rvapp.voyage.ui.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.referenceData.Location
import com.rvapp.voyage.R
import com.rvapp.voyage.model.api.DiscoverFilter
import com.rvapp.voyage.model.api.GeoDBAPI
import com.rvapp.voyage.model.api.WikiMediaAPI
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.util.AmadeusHelper
import kotlinx.coroutines.*
import okhttp3.internal.wait

class DiscoverSharedViewModel(val amadeus: Amadeus) : ViewModel() {
    val amadeusHelper = AmadeusHelper(amadeus)

    var filter: DiscoverFilter = DiscoverFilter()

    private val _ready = MutableLiveData(false)
    val ready: LiveData<Boolean> = _ready

    private val _cities = MutableLiveData<LinkedHashSet<City>>()
    val cities: LiveData<LinkedHashSet<City>> = _cities

    private val _currentCity = MutableLiveData<City>()
    val currentCity: LiveData<City> = _currentCity

    private fun getWikiData(city: City) {
        val api = WikiMediaAPI()
        val data = api.requestEntity(city.wikiDataId)
        city.photo_url = data.cityPhoto
        city.description = data.cityDescription
        city.population = data.population
        city.elevation = data.elevation
        _currentCity.postValue(city)
    }

    fun getGeoCities() {
        _ready.value = false
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = GeoDBAPI().getCities("PT", "10000")
                for (city in response) getWikiData(city)
                _cities.postValue(response)
                _ready.postValue(true)
            }
        }
    }

    //"https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=51.509865&longitude=-0.118092&radius=1&page%5Blimit%5D=10&page%5Boffset%5D=0"
    suspend fun getPOIs(city: City): List<Location>? {
        val result = viewModelScope.async { amadeusHelper.getPOIs(city) }
        return result.await()
    }
}