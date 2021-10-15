package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.fsm.common.StatesManager
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.Ktor.telegramBot
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.coroutineContext


/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviour].
 * Use this method in case you wish to make some additional actions with [flowsUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] or tools for work with webhooks
 *
 * @return Created bot which has been used to create [BehaviourContext] via [buildBehaviour]
 *
 * @see [BehaviourContext]
 * @see [buildBehaviour]
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun telegramBotWithBehaviourAndFSM(
    token: String,
    flowsUpdatesFilter: FlowsUpdatesFilter,
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: BehaviourContextReceiver<Unit>
): TelegramBot = telegramBot(
    token,
    apiUrl,
    builder
).apply {
    buildBehaviourWithFSM(
        flowsUpdatesFilter,
        scope ?: CoroutineScope(coroutineContext),
        defaultExceptionsHandler,
        statesManager,
        presetHandlers,
        block
    )
}

/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviour].
 * Use this method in case you wish to make some additional actions with [flowsUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] or tools for work with webhooks
 *
 * @return Pair of [TelegramBot] and [Job]. This [Job] can be used to stop listening updates in your [block] you passed
 * here
 *
 * @see [BehaviourContext]
 * @see [buildBehaviour]
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun telegramBotWithBehaviourAndFSM(
    token: String,
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: BehaviourContextReceiver<Unit>
): Pair<TelegramBot, Job> {
    return telegramBot(
        token,
        apiUrl,
        builder
    ).let {
        it to it.buildBehaviourWithFSM (
            scope ?: CoroutineScope(coroutineContext),
            defaultExceptionsHandler,
            statesManager,
            presetHandlers,
            block
        )
    }
}
