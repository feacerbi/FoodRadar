package com.felipeacerbi.foodradar.feature_radar.error

import android.content.Context
import com.felipeacerbi.foodradar.feature_radar.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logger: Logger
) {

    fun handle(throwable: Throwable): String {
        logger.log("Error: ${throwable.message}")
        return context.getString(R.string.generic_error_message)
    }
}