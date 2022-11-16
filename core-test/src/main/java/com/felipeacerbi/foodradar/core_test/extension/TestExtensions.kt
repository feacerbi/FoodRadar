package com.felipeacerbi.foodradar.core_test.extension

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
fun runTest(
    testBody: suspend TestScope.() -> Unit
) = runTest(context = UnconfinedTestDispatcher(), testBody = testBody)