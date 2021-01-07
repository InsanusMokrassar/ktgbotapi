package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.updateshandlers.UpdatesFilter
import kotlinx.coroutines.CoroutineScope

typealias BehaviourContextReceiver<T> = suspend BehaviourContext.() -> T
typealias BehaviourContextAndTypeReceiver<T, I> = suspend BehaviourContext.(I) -> T

data class BehaviourContext(
    val bot: TelegramBot,
    val scope: CoroutineScope,
    val flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
) : UpdatesFilter by flowsUpdatesFilter, TelegramBot by bot, CoroutineScope by scope
