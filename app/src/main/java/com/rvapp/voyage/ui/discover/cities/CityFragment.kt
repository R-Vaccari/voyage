package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rvapp.voyage.R
import com.rvapp.voyage.databinding.FragmentCityBinding
import com.rvapp.voyage.ui.discover.DiscoverSharedViewModel
import com.rvapp.voyage.ui.main.MainActivity
import com.squareup.picasso.Picasso

class CityFragment : Fragment() {
    private val discoverViewModel by activityViewModels<DiscoverSharedViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCityBinding.inflate(inflater, container, false)
        val root = binding.root


        val ac: MainActivity = activity as MainActivity
        val banner = root.findViewById<ImageView>(R.id.city_banner)
        return root
    }
}