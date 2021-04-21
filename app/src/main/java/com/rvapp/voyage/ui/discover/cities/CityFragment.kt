package com.rvapp.voyage.ui.discover.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.referenceData.Location
import com.google.android.material.textview.MaterialTextView
import com.rvapp.voyage.R
import com.rvapp.voyage.databinding.FragmentCityBinding
import com.rvapp.voyage.model.entities.City
import com.rvapp.voyage.ui.discover.DiscoverSharedViewModel
import com.rvapp.voyage.ui.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityFragment : Fragment() {
    private val discoverViewModel by navGraphViewModels<DiscoverSharedViewModel>(R.id.nav_city)
    lateinit var amadeus: Amadeus
    lateinit var resultText: MaterialTextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCityBinding.inflate(inflater, container, false)
        val root = binding.root
        val city: City = CityFragmentArgs.fromBundle(requireArguments()).city
        binding.city = city
        //getPOIs(city)


        resultText = root.findViewById(R.id.api_result)
        val banner = root.findViewById<ImageView>(R.id.city_banner)
        Picasso.get().load(city.photo_url).into(banner)
        return root
    }

    private suspend fun setResult(result: ApiResult.Success<*>) {
        withContext(Main) {
            resultText.text = result.data.toString()
        }
    }

    private suspend fun setError(error: String) {
        withContext(Main) {
            resultText.text = error
        }
    }

    //"https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=51.509865&longitude=-0.118092&radius=1&page%5Blimit%5D=10&page%5Boffset%5D=0"
    private fun getPOIs(city: City) {
        CoroutineScope(IO).launch {
            when (val result = amadeus.referenceData.locations.pointsOfInterest.get(51.509865,
                -0.118092, 1, 1, 0)) {
                is ApiResult.Success<*> -> setResult(result)
                else -> setError("Erro")
            }
        }
    }
}