package com.rvapp.voyage.ui.discover

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvapp.voyage.model.api.CountriesCitiesAPI
import com.rvapp.voyage.model.api.GeoDBAPI
import com.rvapp.voyage.model.api.PlacesAPI
import com.rvapp.voyage.model.api.WikiMediaAPI
import com.rvapp.voyage.model.entities.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiscoverViewModel : ViewModel() {
    val api = PlacesAPI()

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _cities = MutableLiveData<Int>()
    val cities: LiveData<Int> = _cities

    private val _picture = MutableLiveData<Bitmap>()
    val picture: LiveData<Bitmap> = _picture

    fun getCitiesByCountryCode(countryCode: String): MutableList<City> {
        val cities = CountriesCitiesAPI.getCitiesFromCountryCode(countryCode)
        _cities.postValue(cities.size)
        return cities
    }

    fun getCityPicture(placeName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photoReference = api.makeRequestByName(placeName)
                val bitmap = api.requestPicture(photoReference)
                _picture.postValue(bitmap)
            }
        }
    }

    fun getWikiData(city: City) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = WikiMediaAPI()
                api.requestEntity(city.wikiDataId)
            }
        }
    }

    fun getGeoCities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = GeoDBAPI()
                api.requestEntity()
            }
        }
    }
}