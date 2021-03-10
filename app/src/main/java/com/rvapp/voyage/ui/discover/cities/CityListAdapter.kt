package com.rvapp.voyage.ui.discover.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rvapp.voyage.databinding.CardCityBinding
import com.rvapp.voyage.model.entities.City

class CityListAdapter(val context: Context): RecyclerView.Adapter<CityListAdapter.ViewHolder>() {


    open class ViewHolder(private val binding: CardCityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.city = city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardCityBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}