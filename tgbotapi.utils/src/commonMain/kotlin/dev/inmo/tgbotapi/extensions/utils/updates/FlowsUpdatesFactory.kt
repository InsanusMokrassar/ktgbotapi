package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter

/**
 * Non-suspendable function for easy-to-use creating of [FlowsUpdatesFilter] and applying the block to it
 *
 * @see flowsUpdatesFilter
 */
inline fun flowsUpdatesFilter(
    internalChannelsSizes: Int = 100,
    block: FlowsUpdatesFilter.() -> Unit,
): FlowsUpdatesFilter {
    val filter = FlowsUpdatesFilter(internalChannelsSizes)
    filter.block()
    return filter
}
