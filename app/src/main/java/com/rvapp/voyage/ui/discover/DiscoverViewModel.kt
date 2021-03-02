package com.rvapp.voyage.ui.discover

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rvapp.voyage.model.api.CountriesCitiesAPI
import com.rvapp.voyage.model.entities.City
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {

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

    fun getCityPicture() {
    }

    fun postPicture(bitmap: Bitmap) {
        _picture.postValue(bitmap)
    }
}