package dev.inmo.tgbotapi.extensions.steps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope

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

suspend fun TelegramBot.buildBehaviour(
    scope: CoroutineScope,
    block: BehaviourContextReceiver<Unit>
) = FlowsUpdatesFilter().also {
    buildBehaviour(
        scope,
        it,
        block
    )
    startGettingOfUpdatesByLongPolling(
        updatesFilter = it,
        scope = scope
    )
}
