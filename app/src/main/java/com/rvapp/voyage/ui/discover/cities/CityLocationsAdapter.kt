package com.rvapp.voyage.ui.discover.cities

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amadeus.android.domain.resources.Location
import com.rvapp.voyage.databinding.CityLocationItemBinding

class CityLocationsAdapter(private val locations: List<Location>): RecyclerView.Adapter<CityLocationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    open class ViewHolder(private val binding: CityLocationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.location = location
        }

    }
}