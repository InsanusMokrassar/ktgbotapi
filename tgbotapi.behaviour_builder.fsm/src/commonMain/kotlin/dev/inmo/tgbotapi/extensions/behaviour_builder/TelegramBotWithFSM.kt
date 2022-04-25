package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.fsm.common.State
import dev.inmo.micro_utils.fsm.common.StatesManager
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.Ktor.telegramBot
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.DefaultTelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.TelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.coroutineContext


/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviourWithFSM].
 * Use this method in case you wish to make some additional actions with [flowsUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] or tools for work with webhooks
 *
 * @return Created bot which has been used to create [BehaviourContext] via [buildBehaviourWithFSM]
 *
 * @see [BehaviourContext]
 * @see [buildBehaviourWithFSM]
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun <T : State> telegramBotWithBehaviourAndFSM(
    token: String,
    flowsUpdatesFilter: FlowsUpdatesFilter,
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
): TelegramBot = telegramBot(
    token,
    apiUrl,
    builder
).apply {
    buildBehaviourWithFSMAndStartLongPolling(
        flowsUpdatesFilter.allUpdatesFlow,
        scope ?: CoroutineScope(coroutineContext),
        defaultExceptionsHandler,
        statesManager,
        presetHandlers,
        telegramHandlersRegistrar,
        block
    )
}

/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviourWithFSMAndStartLongPolling]. This
 * method will launch updates retrieving via long polling inside of [buildBehaviourWithFSMAndStartLongPolling]
 *
 * @return Pair of [TelegramBot] and [Job]. This [Job] can be used to stop listening updates in your [block] you passed
 * here
 *
 * @see BehaviourContext
 * @see buildBehaviourWithFSMAndStartLongPolling
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun <T : State> telegramBotWithBehaviourAndFSMAndStartLongPolling(
    token: String,
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
): Pair<TelegramBot, Job> {
    return telegramBot(
        token,
        apiUrl,
        builder
    ).let {
        it to it.buildBehaviourWithFSMAndStartLongPolling (
            scope ?: CoroutineScope(coroutineContext),
            defaultExceptionsHandler,
            statesManager,
            presetHandlers,
            telegramHandlersRegistrar,
            block
        )
    }
}
