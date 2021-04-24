package com.rvapp.voyage.ui.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadeus.android.Amadeus
import com.amadeus.android.domain.resources.Location
import com.rvapp.voyage.model.api.DiscoverFilter
import com.rvapp.voyage.model.api.GeoDBAPI
import com.rvapp.voyage.model.api.WikiMediaAPI
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.model.api.AmadeusAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiscoverSharedViewModel(val amadeus: Amadeus) : ViewModel() {
    val amadeusHelper = AmadeusAPI(amadeus)

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

    suspend fun getPOIs(city: City): List<Location>? {
        val result = viewModelScope.async { amadeusHelper.getPOIs(city) }
        return result.await()
    }

    suspend fun getPOIsDirectly(city: City): String? {
        val string = viewModelScope.async { amadeusHelper.getPOIsDirectly(city) }
        return string.await()
    }
}