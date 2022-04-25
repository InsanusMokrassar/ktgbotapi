package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.Ktor.telegramBot
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.DefaultTelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.TelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.telegramBotAPIDefaultUrl
import kotlinx.coroutines.*
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
suspend fun telegramBotWithBehaviour(
    token: String,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: BehaviourContextReceiver<Unit>
): TelegramBot = telegramBot(
    token,
    apiUrl,
    builder
).apply {
    buildBehaviour(
        flowsUpdatesFilter,
        scope ?: CoroutineScope(coroutineContext),
        defaultExceptionsHandler,
        telegramHandlersRegistrar,
        block
    )
}

/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviourWithLongPolling].
 * Use this method in case you wish to make some additional actions with [flowsUpdatesFilter].
 *
 * **WARNING** This method WILL launch updates listening inside of calling [buildBehaviourWithLongPolling]
 *
 * @return Pair of [TelegramBot] and [Job]. This [Job] can be used to stop listening updates in your [block] you passed
 * here
 *
 * @see [BehaviourContext]
 * @see buildBehaviourWithLongPolling
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun telegramBotWithBehaviourAndLongPolling(
    token: String,
    scope: CoroutineScope? = null,
    apiUrl: String = telegramBotAPIDefaultUrl,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {},
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: BehaviourContextReceiver<Unit>
): Pair<TelegramBot, Job> {
    return telegramBot(
        token,
        apiUrl,
        builder
    ).let {
        it to it.buildBehaviourWithLongPolling(
            scope ?: CoroutineScope(coroutineContext),
            defaultExceptionsHandler,
            telegramHandlersRegistrar,
            block
        )
    }
}
