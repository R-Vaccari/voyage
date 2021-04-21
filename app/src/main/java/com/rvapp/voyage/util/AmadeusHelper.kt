package com.rvapp.voyage.util

import android.content.Context
import com.amadeus.android.Amadeus

class AmadeusHelper(val context: Context) {
    val amadeus: Amadeus = Amadeus.Builder(context)
        .build()
}