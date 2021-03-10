package com.rvapp.voyage.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvapp.voyage.model.api.GeoDBAPI
import com.rvapp.voyage.model.api.WikiMediaAPI
import com.rvapp.voyage.model.entities.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiscoverSharedViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _cities = MutableLiveData<LinkedHashSet<City>>()
    val cities: LiveData<LinkedHashSet<City>> = _cities

    private val _currentCity = MutableLiveData<City>()
    val currentCity: LiveData<City> = _currentCity

    private val _picture = MutableLiveData<String>()
    val picture: LiveData<String> = _picture

    fun getWikiData(city: City) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = WikiMediaAPI()
                val data = api.requestEntity(city.wikiDataId)
                city.photo_url = data.cityPhoto
                city.description = data.cityDescription
                _currentCity.postValue(city)
            }
        }
    }

    fun getGeoCities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = GeoDBAPI().getCities("PT", "10000")
                _cities.postValue(response)
            }
        }
    }
}