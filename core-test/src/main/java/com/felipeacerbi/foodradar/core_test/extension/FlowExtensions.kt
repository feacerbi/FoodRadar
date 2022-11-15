package com.felipeacerbi.foodradar.core_test.extension

import com.felipeacerbi.foodradar.core_test.flow.FlowTester
import com.felipeacerbi.foodradar.core_test.flow.FlowTester.FlowSize.Multiple
import com.felipeacerbi.foodradar.core_test.flow.FlowTester.FlowSize.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
inline fun <T : Any> CoroutineScope.flowTester(
    count: Int = 0,
    block: () -> Flow<T>
) = flowTester(count, block.invoke())

@ExperimentalCoroutinesApi
fun <T : Any> CoroutineScope.flowTester(
    count: Int = 0,
    flow: Flow<T>
) = FlowTester(flow).apply {
    require(count >= 0) { "Flow count can only be positive." }
    startFlow(size = if (count == 0) Single else Multiple(count))
}
