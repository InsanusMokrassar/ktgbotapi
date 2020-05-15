package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter

/**
 * Non-suspendable function for easy-to-use creating of [FlowsUpdatesFilter] and applying the block to it
 */
fun flowsUpdatesFilter(
    internalChannelsSizes: Int = 64,
    block: FlowsUpdatesFilter.() -> Unit
): FlowsUpdatesFilter = FlowsUpdatesFilter(internalChannelsSizes).apply(block)

/**
 * Suspend variation for [flowsUpdatesFilter] function
 *
 * @see flowsUpdatesFilter
 */
suspend fun flowsUpdatesFilter(
    internalChannelsSizes: Int = 64,
    block: suspend FlowsUpdatesFilter.() -> Unit
): FlowsUpdatesFilter {
    val filter = FlowsUpdatesFilter(internalChannelsSizes)
    filter.block()
    return filter
}
