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
import com.rvapp.voyage.model.entities.OptionsItem
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverFactory
import com.rvapp.voyage.ui.discover.viewmodel.DiscoverSharedViewModel
import com.rvapp.voyage.ui.main.MainActivity

class OptionsFragment: Fragment() {
    lateinit var viewPager: ViewPager2
    lateinit var mediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        switchTabVisibility()
        val items = OptionsFragmentArgs.fromBundle(requireArguments()).items
        viewPager = view.findViewById(R.id.discover_options_viewpager)
        viewPager.adapter = OptionsAdapter(this, items)
        attachTab(items)
    }

    override fun onDetach() {
        switchTabVisibility()
        if (mediator.isAttached) detachTab()
        super.onDetach()
    }

    private fun switchTabVisibility() {
        val tab = (activity as MainActivity).tabLayout
        if (tab.visibility == View.GONE) tab.visibility = View.VISIBLE
        else tab.visibility = View.GONE
    }

    private fun attachTab(items: Array<OptionsItem>) {
        mediator = TabLayoutMediator((activity as MainActivity).tabLayout, viewPager) { tab: TabLayout.Tab, i: Int -> tab.text = items[i].name }
        mediator.attach()
    }

    private fun detachTab() = mediator.detach()
}