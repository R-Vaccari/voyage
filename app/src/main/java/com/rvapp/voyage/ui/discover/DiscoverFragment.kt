package com.rvapp.voyage.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.rvapp.voyage.R
import com.rvapp.voyage.model.entities.City

class DiscoverFragment : Fragment() {
    private val discoverViewModel by activityViewModels<DiscoverSharedViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_discover, container, false)
        root.findViewById<MaterialButton>(R.id.text_gallery).setOnClickListener {
            discoverViewModel.getGeoCities()
        }
        val btNext: MaterialButton = root.findViewById(R.id.advance_bt)
        btNext.setOnClickListener {
            findNavController().navigate(R.id.action_nav_discover_to_nav_city_list)
        }

        discoverViewModel.ready.observe(viewLifecycleOwner) {
            btNext.isEnabled = it
        }
        return root
    }
}