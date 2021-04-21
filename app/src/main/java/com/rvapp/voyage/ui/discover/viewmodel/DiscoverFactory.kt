package com.rvapp.voyage.ui.discover.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amadeus.android.Amadeus

class DiscoverFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass.getConstructor(Amadeus::class.java).newInstance(Amadeus.Builder(context).build())
}