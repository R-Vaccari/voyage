package com.rvapp.voyage.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvapp.voyage.model.api.GeoDBAPI
import com.rvapp.voyage.model.api.PlacesAPI
import com.rvapp.voyage.model.api.WikiMediaAPI
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.util.HashHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class DiscoverSharedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    private val _currentCity = MutableLiveData<City>()
    val currentCity: LiveData<City> = _currentCity

    private val _picture = MutableLiveData<String>()
    val picture: LiveData<String> = _picture

    /*
    fun getCityPicture(placeName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photoReference = api.makeRequestByName(placeName)
                val bitmap = api.requestPicture(photoReference)
            }
        }
    }

     */

    suspend fun getWikiData(city: City) {
        val api = WikiMediaAPI()
        val city = api.requestEntity(city.wikiDataId)

    }

    fun getGeoCities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = GeoDBAPI().getCities("PT", "10000")
                _cities.postValue(response)
                val wikiData = getWikiData(response[0])
            }
        }
    }
}