package com.rvapp.voyage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.rvapp.voyage.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val editText: TextInputEditText = root.findViewById(R.id.text_home)
        val tvCities = root.findViewById<MaterialTextView>(R.id.tv_cities_count)
        homeViewModel.cities.observe(viewLifecycleOwner, Observer {
            tvCities.text = it.toString()
        })
        root.findViewById<MaterialButton>(R.id.discover_bt).setOnClickListener {
            it.findNavController().navigate(R.id.action_nav_home_to_nav_discover)
        }
        return root
    }
}