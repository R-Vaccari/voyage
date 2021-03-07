package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rvapp.voyage.R
import com.rvapp.voyage.ui.discover.DiscoverSharedViewModel
import com.squareup.picasso.Picasso

class CityFragment : Fragment() {
    private val discoverViewModel by activityViewModels<DiscoverSharedViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_city, container, false)
        val banner = root.findViewById<ImageView>(R.id.city_banner)
        discoverViewModel.picture.observe(viewLifecycleOwner) {
            Picasso.get().load(discoverViewModel.picture.value).into(banner)
        }
        return root
    }
}