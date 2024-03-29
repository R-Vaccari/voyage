package com.rvapp.voyage.ui.discover.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.rvapp.voyage.databinding.CityLocationItemBinding
import com.rvapp.voyage.model.entities.Location

class CityLocationsAdapter(private val locations: LinkedHashSet<Location>,
                           private val context: Context
                           ): RecyclerView.Adapter<CityLocationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CityLocationItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(locations.elementAt(position))

    override fun getItemCount(): Int = locations.size

    open class ViewHolder(private val binding: CityLocationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.location = location
        }
    }
}