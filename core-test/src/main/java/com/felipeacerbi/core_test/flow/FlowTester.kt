package com.felipeacerbi.core_test.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

@ExperimentalCoroutinesApi
class FlowTester<T : Any>(
    private val flow: Flow<T>
) {

    val values: List<T> get() = results
    private val results = mutableListOf<T>()

    internal suspend fun startFlow(size: FlowSize) {
        when (size) {
            is FlowSize.Single -> results.add(flow.single())
            is FlowSize.Multiple -> flow.take(size.count).toList(results)
        }
    }

    sealed class FlowSize {
        object Single : FlowSize()
        data class Multiple(val count: Int): FlowSize()
    }
}