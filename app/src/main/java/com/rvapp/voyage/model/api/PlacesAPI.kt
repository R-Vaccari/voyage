package com.rvapp.voyage.model.api

import okhttp3.OkHttpClient
import okhttp3.Request

class PlacesAPI {

    companion object {
        const val URL_FIND_PLACE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
        const val KEY = "key=AIzaSyBpKS61WJqYA-ePqjXKeaa4snhSC4oXEtM"
        const val INPUT = "&input="
        const val INPUT_TYPE = "&inputtype=textquery"
        const val FIELDS = "&fields="

        fun makeRequestByName(name: String) {
            val client = OkHttpClient()
            val target = StringBuilder().append(URL_FIND_PLACE).append(KEY).append(INPUT).append(name).append(INPUT_TYPE)
                    .append(FIELDS).append("place_id,photos").toString()
            val request = Request.Builder()
                    .url(target)
                    .get()
                    .build()

            val response = client.newCall(request).execute()
            val result = response.body!!.string()
            response.close()
        }
    }
}