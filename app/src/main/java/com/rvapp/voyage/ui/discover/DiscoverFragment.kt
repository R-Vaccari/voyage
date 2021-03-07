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
        val city = City("Q597", "Lisbon", 0.0, 0.0)
        root.findViewById<MaterialButton>(R.id.text_gallery).setOnClickListener {
            discoverViewModel.getGeoCities()
        }
        return root
    }
}