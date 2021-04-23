package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amadeus.android.domain.resources.Location
import com.google.android.material.textview.MaterialTextView
import com.rvapp.voyage.R
import com.rvapp.voyage.databinding.FragmentCityBinding
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CityFragment : Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.discover_graph) { DiscoverFactory(requireContext()) }
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
        val resultText = root.findViewById<MaterialTextView>(R.id.api_result)
        val banner = root.findViewById<ImageView>(R.id.city_banner)
        Picasso.get().load(city.photo_url).into(banner)

        MainScope().launch {
            val locations = discoverViewModel.getPOIs(city)
            if (locations != null) setRecycler(root, locations)
            else resultText.text = resources.getString(R.string.no_pois)
        }
        return root
    }

    private fun setRecycler(root: View, locations: List<Location>) {
        val recycler = root.findViewById<RecyclerView>(R.id.city_recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = CityLocationsAdapter(locations)
    }
}