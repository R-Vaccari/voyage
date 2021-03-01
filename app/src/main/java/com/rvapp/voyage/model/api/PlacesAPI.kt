package com.rvapp.voyage.model.api

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class PlacesAPI {

    companion object {
        const val URL_FIND_PLACE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
        const val URL_GET_PHOTO = "https://maps.googleapis.com/maps/api/place/photo?"
        const val KEY = "key=AIzaSyBpKS61WJqYA-ePqjXKeaa4snhSC4oXEtM"
        const val INPUT = "&input="
        const val INPUT_TYPE = "&inputtype=textquery"
        const val FIELDS = "&fields="
        const val PHOTO_REFERENCE = "&photoreference="
        const val MAX_WIDTH = "&maxwidth=200"
        const val MAX_HEIGHT = "&maxheight=200"

        fun makeRequestByName(name: String) {
            val client = OkHttpClient()
            val target = StringBuilder().append(URL_FIND_PLACE).append(KEY).append(INPUT).append(name).append(INPUT_TYPE)
                    .append(FIELDS).append("place_id,photos").toString()
            val request = Request.Builder()
                    .url(target)
                    .get()
                    .build()

            val response = client.newCall(request).execute()
            val placeData: JSONObject = JSONObject(response.body!!.string()).getJSONArray("candidates").get(0) as JSONObject
            val photos: JSONObject = placeData.getJSONArray("photos").get(0) as JSONObject
            requestPicture(photos.optString("photo_reference"))
            response.close()
        }

        fun requestPicture(photoReference: String) {
            val client = OkHttpClient()
            val target = StringBuilder().append(URL_GET_PHOTO).append(KEY).append(PHOTO_REFERENCE).append(photoReference).append(MAX_WIDTH)
                    .append(MAX_HEIGHT).toString()
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