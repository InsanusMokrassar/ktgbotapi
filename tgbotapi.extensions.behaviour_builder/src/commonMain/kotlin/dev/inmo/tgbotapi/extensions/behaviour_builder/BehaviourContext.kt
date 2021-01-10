package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.updateshandlers.UpdatesFilter
import kotlinx.coroutines.CoroutineScope

typealias BehaviourContextReceiver<T> = suspend BehaviourContext.() -> T
typealias BehaviourContextAndTypeReceiver<T, I> = suspend BehaviourContext.(I) -> T

/**
 * This class contains all necessary tools for work with bots and especially for [buildBehaviour]
 *
 * @param scope This param will be used for creating of some subscriptions inside of methods, updates listening and
 * different other things in context of working with [CoroutineScope] and coroutines.
 * @param flowsUpdatesFilter This parameter will be used to subscribe on different types of update
 */
data class BehaviourContext(
    val bot: TelegramBot,
    val scope: CoroutineScope,
    val flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
) : UpdatesFilter by flowsUpdatesFilter, TelegramBot by bot, CoroutineScope by scope
