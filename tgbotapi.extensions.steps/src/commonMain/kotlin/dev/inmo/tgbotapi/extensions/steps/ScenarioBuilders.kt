package dev.inmo.tgbotapi.extensions.steps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope

suspend fun TelegramBot.buildScenarios(
    scope: CoroutineScope,
    flowUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    block: ScenarioReceiver<Unit>
) {
    Scenario(
        this,
        flowUpdatesFilter,
        scope
    ).block()
}
