package com.felipeacerbi.foodradar.core_test.rule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    private val testScope = TestScope(testDispatcher)

    fun runTest(
        dispatchTimeoutMs: Long = 60_000L,
        testBody: suspend TestScope.() -> Unit
    ) = testScope.runTest(dispatchTimeoutMs, testBody)

    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}