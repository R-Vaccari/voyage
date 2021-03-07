package com.rvapp.voyage.model.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class PlacesAPI() {

    val URL_FIND_PLACE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
    val URL_GET_PHOTO = "https://maps.googleapis.com/maps/api/place/photo?"
    val KEY = "key=AIzaSyBpKS61WJqYA-ePqjXKeaa4snhSC4oXEtM"
    val INPUT = "&input="
    val INPUT_TYPE = "&inputtype=textquery"
    val FIELDS = "&fields="
    val PHOTO_REFERENCE = "&photoreference="
    val MAX_WIDTH = "&maxwidth=200"
    val MAX_HEIGHT = "&maxheight=200"

    fun makeRequestByName(name: String): String {
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
        response.close()
        return photos.optString("photo_reference")
    }

    fun requestPicture(photoReference: String): Bitmap {
        val client = OkHttpClient()
        val target = StringBuilder().append(URL_GET_PHOTO).append(KEY).append(PHOTO_REFERENCE).append(photoReference).append(MAX_WIDTH)
                .append(MAX_HEIGHT).toString()
        val request = Request.Builder()
                .url(target)
                .get()
                .build()

        val response = client.newCall(request).execute()
        val bitmap = response.body!!.bytes()
        response.close()
        return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.size)
    }
}