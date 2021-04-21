package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rvapp.voyage.R
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel

class CityListFragment : Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.discover_graph) { DiscoverFactory(requireContext()) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list_city, container, false)
        setRecycler(root)

        return root
    }

    private fun setRecycler(root: View) {
        val recycler = root.findViewById<RecyclerView>(R.id.rv_list_city)
        val adapter = CityListAdapter(requireContext(), discoverViewModel.cities.value!!)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
    }
}