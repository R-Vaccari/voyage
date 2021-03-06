package com.rvapp.voyage.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.rvapp.voyage.R
import com.rvapp.voyage.model.entities.City
import com.squareup.picasso.Picasso

class DiscoverFragment : Fragment() {
    private lateinit var discoverViewModel: DiscoverViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        discoverViewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_discover, container, false)
        val city = City("Q597", "Lisbon", 0.0, 0.0, 500000, "Europe")
        val picture = root.findViewById<ImageView>(R.id.picture)
        root.findViewById<MaterialButton>(R.id.text_gallery).setOnClickListener {
            discoverViewModel.getWikiData(city)
        }
        discoverViewModel.picture.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(picture)
        }
        return root
    }
}