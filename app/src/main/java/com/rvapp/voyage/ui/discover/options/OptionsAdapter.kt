package com.rvapp.voyage.ui.discover.options

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rvapp.voyage.model.entities.OptionsItem

class OptionsAdapter(val fragment: Fragment,
                     val items: Array<OptionsItem>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return OptionsItemFragment(items[position])
    }
}