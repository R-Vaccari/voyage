package com.rvapp.voyage.ui.discover

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
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
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<ImageButton>(R.id.discover_experience_bt).setOnClickListener {
            val imageButton = it as ImageButton
            (imageButton.drawable as AnimatedVectorDrawable).start()

            it.findNavController().navigate(DiscoverFragmentDirections.actionNavDiscoverToNavOptions(
                arrayOf(OptionsItem("Mediterranean", R.drawable.experience_mediterranean, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
                    OptionsItem("Black Sea", R.drawable.experience_black_sea, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
                    OptionsItem("Northern Lights", R.drawable.experience_north, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")))) }

    }
}

