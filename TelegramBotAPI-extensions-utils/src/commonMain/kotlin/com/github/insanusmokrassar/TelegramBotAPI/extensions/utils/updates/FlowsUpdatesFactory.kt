package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter

/**
 * Non-suspendable function for easy-to-use creating of [FlowsUpdatesFilter] and applying the block to it
 *
 * @see flowsUpdatesFilter
 */
inline fun flowsUpdatesFilter(
    internalChannelsSizes: Int = 100,
    block: FlowsUpdatesFilter.() -> Unit
): FlowsUpdatesFilter {
    val filter = FlowsUpdatesFilter(internalChannelsSizes)
    filter.block()
    return filter
}
