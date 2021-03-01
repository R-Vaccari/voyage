package com.rvapp.voyage.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.rvapp.voyage.R
import com.rvapp.voyage.model.api.PlacesAPI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DiscoverFragment : Fragment() {
    private lateinit var homeViewModel: DiscoverViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_discover, container, false)
        root.findViewById<MaterialButton>(R.id.text_gallery).setOnClickListener {
            GlobalScope.launch { PlacesAPI.makeRequestByName("Lisbon Portugal") }
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            root.findViewById<ImageView>(R.id.picture)
        }
        return root
    }
}