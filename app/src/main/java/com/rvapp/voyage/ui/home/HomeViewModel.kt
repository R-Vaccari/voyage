package com.rvapp.voyage.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rvapp.voyage.model.api.CountriesCitiesAPI
import com.rvapp.voyage.model.entities.City
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _cities = MutableLiveData<Int>()
    val cities: LiveData<Int> = _cities

    fun getCitiesByCountryCode(countryCode: String): MutableList<City> {
        val cities = CountriesCitiesAPI.getCitiesFromCountryCode(countryCode)
        _cities.postValue(cities.size)
        return cities
    }
}