package com.rvapp.voyage.model.entities

import android.os.Parcel
import android.os.Parcelable
import com.amadeus.android.domain.resources.Location
import com.google.gson.annotations.SerializedName

data class City(@SerializedName("wikiDataId") val wikiDataId: String,
                @SerializedName("name") val name: String,
                @SerializedName("latitude") val latitude: Double,
                @SerializedName("longitude") val longitude: Double): Parcelable {

    lateinit var description: String
    lateinit var photo_url: String
    lateinit var pointsOfInterest: List<Location>
    var population: Int = -100
    var elevation: Int = -100

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
        description = parcel.readString().toString()
        photo_url = parcel.readString().toString()
        population = parcel.readInt()
        elevation = parcel.readInt()
    }

    override fun toString(): String {
        return "[$name]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(wikiDataId)
        parcel.writeString(name)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(description)
        parcel.writeString(photo_url)
        parcel.writeInt(population)
        parcel.writeInt(elevation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}