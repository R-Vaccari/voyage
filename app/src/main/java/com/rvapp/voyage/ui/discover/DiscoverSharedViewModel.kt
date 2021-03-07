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
    val api = PlacesAPI()

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _cities = MutableLiveData<Int>()
    val cities: LiveData<Int> = _cities

    private val _picture = MutableLiveData<String>()
    val picture: LiveData<String> = _picture

    fun getCityPicture(placeName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photoReference = api.makeRequestByName(placeName)
                val bitmap = api.requestPicture(photoReference)
            }
        }
    }

    fun getWikiData(city: City) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = WikiMediaAPI()
                val jsonObject: JSONObject = JSONObject(api.requestEntity(city.wikiDataId)).getJSONObject("entities").get(city.wikiDataId) as JSONObject
                city.description = jsonObject.getJSONObject("descriptions").getJSONObject("en").optString("value")
                city.photo_url = jsonObject.getJSONObject("claims")
                        .getJSONArray("P948")
                        .getJSONObject(0)
                        .getJSONObject("mainsnak")
                        .getJSONObject("datavalue")
                        .optString("value")
                        .replace(" ", "_")
                val mdHash = HashHelper.md5(city.photo_url)
                val a = mdHash[0]
                val b = mdHash[1]
                _picture.postValue("https://upload.wikimedia.org/wikipedia/commons/$a/$a$b/${city.photo_url}")
            }
        }
    }

    fun getGeoCities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = GeoDBAPI().getCities("PT", "10000")
            }
        }
    }
}