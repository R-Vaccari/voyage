package com.rvapp.voyage.ui.discover.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.rvapp.voyage.R
import com.rvapp.voyage.databinding.FragmentDiscoverOptionsItemBinding
import com.rvapp.voyage.model.entities.OptionsItem

class OptionsItemFragment(val item: OptionsItem): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiscoverOptionsItemBinding.inflate(inflater, container, false)
        binding.item = item
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}