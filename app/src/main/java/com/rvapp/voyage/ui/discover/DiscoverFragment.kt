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
import com.google.android.material.button.MaterialButtonToggleGroup
import com.rvapp.voyage.R
import com.rvapp.voyage.model.entities.City

class DiscoverFragment : Fragment() {
    private val discoverViewModel by activityViewModels<DiscoverSharedViewModel>()
    private var lock01 = false
    private var lock02 = false
    private var lock03 = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_discover, container, false)
        groupListeners(root)
        initButtons(root)
        return root
    }

    private fun groupListeners(root: View) {
        root.findViewById<MaterialButtonToggleGroup>(R.id.discover_toggle_experience)
            .addOnButtonCheckedListener { _: MaterialButtonToggleGroup, i: Int, b: Boolean ->
            lock01 = b
            if (lock01 && lock02 && lock03) discoverViewModel.getGeoCities()
        }

        root.findViewById<MaterialButtonToggleGroup>(R.id.discover_toggle_pop)
            .addOnButtonCheckedListener { _: MaterialButtonToggleGroup, i: Int, b: Boolean ->
            lock02 = b
            if (lock01 && lock02 && lock03) discoverViewModel.getGeoCities()
        }

        root.findViewById<MaterialButtonToggleGroup>(R.id.discover_toggle_elevation)
            .addOnButtonCheckedListener { _: MaterialButtonToggleGroup, i: Int, b: Boolean ->
            lock03 = b
            if (lock01 && lock02 && lock03) discoverViewModel.getGeoCities()
        }
    }

    private fun initButtons(root: View) {
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


        root.findViewById<MaterialButton>(R.id.discover_bt_experience_max).addOnCheckedChangeListener { materialButton: MaterialButton, b: Boolean ->
            discoverViewModel.filter.experience = "North"
        }
        root.findViewById<MaterialButton>(R.id.discover_bt_population_max).addOnCheckedChangeListener { materialButton: MaterialButton, b: Boolean ->
            discoverViewModel.filter.population = 10000
        }
        root.findViewById<MaterialButton>(R.id.discover_bt_elevation_max).addOnCheckedChangeListener { materialButton: MaterialButton, b: Boolean ->
            discoverViewModel.filter.elevation = 1000
        }
    }
}

