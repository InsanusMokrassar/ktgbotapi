package dev.inmo.tgbotapi.extensions.steps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope

typealias ScenarioReceiver<T> = suspend Scenario.() -> T
typealias ScenarioAndTypeReceiver<T, I> = suspend Scenario.(I) -> T

data class Scenario(
    val bot: TelegramBot,
    val flowsUpdatesFilter: FlowsUpdatesFilter,
    val scope: CoroutineScope
)


