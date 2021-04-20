package com.rvapp.voyage.ui.discover.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rvapp.voyage.databinding.CardCityBinding
import com.rvapp.voyage.model.entities.City

class CityListAdapter(val context: Context,
                      val cities: LinkedHashSet<City>
                      ): RecyclerView.Adapter<CityListAdapter.ViewHolder>() {


    open class ViewHolder(private val binding: CardCityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.city = city
            binding.root.setOnClickListener {
                it.findNavController().navigate(CityListFragmentDirections.actionCityListToDetails(city))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardCityBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(cities.elementAt(position))

    override fun getItemCount(): Int = cities.size
}