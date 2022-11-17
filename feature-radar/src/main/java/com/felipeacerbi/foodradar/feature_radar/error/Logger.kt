package com.felipeacerbi.foodradar.feature_radar.error

import android.util.Log
import javax.inject.Inject

class Logger @Inject constructor() {

    fun log(message: String) {
        Log.d("Logger", message)
    }
}