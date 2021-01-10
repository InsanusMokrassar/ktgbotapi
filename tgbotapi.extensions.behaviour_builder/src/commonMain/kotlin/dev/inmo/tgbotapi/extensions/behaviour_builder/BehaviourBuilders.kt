package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.CoroutineScope

/**
 * Use this method in case you wish to make some additional actions with [flowUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] or tools for work with webhooks
 *
 * @see [BehaviourContext]
 * @see startGettingOfUpdatesByLongPolling
 */
@PreviewFeature
suspend fun TelegramBot.buildBehaviour(
    scope: CoroutineScope,
    flowUpdatesFilter: FlowsUpdatesFilter,
    block: BehaviourContextReceiver<Unit>
) {
    BehaviourContext(
        this,
        scope,
        flowUpdatesFilter
    ).block()
}

/**
 * Use this method to build bot behaviour and run it via long polling. In case you wish to get [FlowsUpdatesFilter] for
 * additional manipulations, you must provide external [FlowsUpdatesFilter] in other [buildBehaviour] function.
 *
 * @see buildBehaviour
 * @see BehaviourContext
 * @see startGettingOfUpdatesByLongPolling
 */
@PreviewFeature
suspend fun TelegramBot.buildBehaviour(
    scope: CoroutineScope,
    block: BehaviourContextReceiver<Unit>
) = FlowsUpdatesFilter().let {
    buildBehaviour(
        scope,
        it,
        block
    )
    longPolling(
        it,
        scope = scope
    )
}
