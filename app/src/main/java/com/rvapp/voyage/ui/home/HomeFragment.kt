package com.rvapp.voyage.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.rvapp.voyage.R
import com.rvapp.voyage.model.entities.City
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        root.findViewById<MaterialButton>(R.id.import_bt).setOnClickListener {
            val client = OkHttpClient()

            val request = Request.Builder()
                    .url("https://countries-cities.p.rapidapi.com/location/country/PT/city/list")
                    .get()
                    .addHeader("x-rapidapi-key", "73a22a72d3msh9a0f3e37accb5abp1b198ajsn7bdc106de086")
                    .addHeader("x-rapidapi-host", "countries-cities.p.rapidapi.com")
                    .build()

            Thread {
                val response = client.newCall(request).execute()
                val cities = parse(response.body!!.string())
                Log.d(null, cities.toString())
            }.start()
        }

        return root
    }

    fun parse(body: String) : MutableList<City> {
        val cities = mutableListOf<City>()
        val array = JSONObject(body).getJSONArray("cities")
        for (i in 0 until array.length()) {
            val jsonObject = array.getJSONObject(i)
            val city = City(jsonObject.optInt("geonameid"), jsonObject.optString("name"),
                jsonObject.optString("latitude"), jsonObject.optString("longitude"), jsonObject.optInt("population"), jsonObject.optString("timezone"))
            cities.add(city)
        }
        return cities
    }
}