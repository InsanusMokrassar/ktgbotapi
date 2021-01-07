package dev.inmo.tgbotapi.extensions.steps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope

typealias BehaviourContextReceiver<T> = suspend BehaviourContext.() -> T
typealias BehaviourContextAndTypeReceiver<T, I> = suspend BehaviourContext.(I) -> T

data class BehaviourContext(
    val bot: TelegramBot,
    val scope: CoroutineScope,
    val flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
)
