package com.felipeacerbi.foodradar.core_test.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class FlowTester<T : Any>(
    private val flow: Flow<T>
) {

    val values: List<T> get() = results
    private val results = mutableListOf<T>()

    internal fun CoroutineScope.startFlow(size: FlowSize) = launch {
        when (size) {
            is FlowSize.Single -> results.add(flow.first())
            is FlowSize.Multiple -> flow.take(size.count).toList(results)
        }
    }

    sealed class FlowSize {
        object Single : FlowSize()
        data class Multiple(val count: Int): FlowSize()
    }
}