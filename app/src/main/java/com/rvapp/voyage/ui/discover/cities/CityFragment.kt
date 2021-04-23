package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.referenceData.Location
import com.google.android.material.textview.MaterialTextView
import com.rvapp.voyage.R
import com.rvapp.voyage.databinding.FragmentCityBinding
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class CityFragment : Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.discover_graph) { DiscoverFactory(requireContext()) }
    lateinit var resultText: MaterialTextView
    lateinit var city: City

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)
        city = CityFragmentArgs.fromBundle(requireArguments()).city
        binding.city = city
        val root = binding.root

        resultText = root.findViewById(R.id.api_result)
        val banner = root.findViewById<ImageView>(R.id.city_banner)
        Picasso.get().load(city.photo_url).into(banner)
        return root
    }

    override fun onStart() {
        MainScope().launch {
            val locations =  discoverViewModel.getPOIs(city)
            if (locations != null) resultText.text = locations.toString()
            else resultText.text = R.string.no_pois.toString()
        }
        super.onStart()
    }
}