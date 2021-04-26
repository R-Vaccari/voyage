package com.rvapp.voyage.model.entities

import android.os.Parcel
import android.os.Parcelable

data class OptionsItem(val name: String,
                       val imageId: Int,
                       val description: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(imageId)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OptionsItem> {
        override fun createFromParcel(parcel: Parcel): OptionsItem {
            return OptionsItem(parcel)
        }

        override fun newArray(size: Int): Array<OptionsItem?> {
            return arrayOfNulls(size)
        }
    }
}