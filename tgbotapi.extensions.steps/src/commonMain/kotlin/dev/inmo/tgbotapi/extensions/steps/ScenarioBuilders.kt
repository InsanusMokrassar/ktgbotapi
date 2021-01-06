package dev.inmo.tgbotapi.extensions.steps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingFlowsUpdatesByLongPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope

suspend fun TelegramBot.buildScenarios(
    scope: CoroutineScope,
    flowUpdatesFilter: FlowsUpdatesFilter,
    block: ScenarioReceiver<Unit>
) {
    Scenario(
        this,
        scope,
        flowUpdatesFilter
    ).block()
}

suspend fun TelegramBot.buildScenarios(
    scope: CoroutineScope,
    block: ScenarioReceiver<Unit>
) = FlowsUpdatesFilter().also {
    buildScenarios(
        scope,
        it,
        block
    )
    startGettingOfUpdatesByLongPolling(
        updatesFilter = it,
        scope = scope
    )
}
