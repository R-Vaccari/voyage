package com.rvapp.voyage.ui.discover.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rvapp.voyage.R
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel

class OptionsFragment: Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.discover_graph) { DiscoverFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tab: TabLayout = view.findViewById(R.id.discover_options_tab)
        val items = OptionsFragmentArgs.fromBundle(requireArguments()).items
        val viewPager: ViewPager2 = view.findViewById(R.id.discover_options_viewpager)
        viewPager.adapter = OptionsAdapter(this, items)
        TabLayoutMediator(tab, viewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = items[i].name
        }.attach()
    }
}