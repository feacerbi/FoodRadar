package com.felipeacerbi.foodradar.feature_radar.error

import android.content.Context
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.Assert.assertEquals
import org.junit.Test

class ErrorHandlerTest {

    private val context = mockk<Context>()
    private val logger = mockk<Logger>()

    private val errorHandler = ErrorHandler(
        context,
        logger
    )

    @Test
    fun `Given an error Then throws a generic exception`() {
        val expectedMessage = "error"
        every { logger.log(any()) } just runs
        every { context.getString(any()) } returns expectedMessage

        val result = errorHandler.handle(Exception())

        assertEquals(expectedMessage, result)
    }
}