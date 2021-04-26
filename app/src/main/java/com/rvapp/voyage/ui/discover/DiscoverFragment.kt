package com.rvapp.voyage.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.rvapp.voyage.R
import com.rvapp.voyage.model.entities.OptionsItem
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel

class DiscoverFragment : Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.discover_graph) { DiscoverFactory(requireContext()) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_discover, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageButton>(R.id.discover_experience_bt).setOnClickListener {
            it.findNavController().navigate(DiscoverFragmentDirections.actionNavDiscoverToNavOptions(
                arrayOf(OptionsItem("Mediterranean", R.drawable.experience_mediterranean, ""), OptionsItem("Black Sea", R.drawable.experience_black_sea, ""),
                    OptionsItem("Northern Lights", R.drawable.experience_north, "")))) }

    }
}

