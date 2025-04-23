package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.update.abstracts.Update
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
    testServer: Boolean = false,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    block: BehaviourContextReceiver<Unit>,
): TelegramBot = telegramBot(
    token,
    apiUrl,
    testServer,
    builder,
).apply {
    buildBehaviour(
        flowUpdatesFilter = flowsUpdatesFilter,
        scope = scope ?: CoroutineScope(coroutineContext),
        defaultExceptionsHandler = defaultExceptionsHandler,
        subcontextInitialAction = subcontextInitialAction,
        block = block,
    )
}

/**
 * Create bot using [telegramBot] and start listening for updates using [buildBehaviourWithLongPolling].
 * Use this method in case you wish to make some additional actions with [flowsUpdatesFilter].
 *
 * **WARNING** This method WILL launch updates listening inside of calling [buildBehaviourWithLongPolling]
 *
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
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
    testServer: Boolean = false,
    timeoutSeconds: Seconds = 30,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    block: BehaviourContextReceiver<Unit>,
): Pair<TelegramBot, Job> {
    return telegramBot(
        token,
        apiUrl,
        testServer,
        builder,
    ).let {
        it to
            it.buildBehaviourWithLongPolling(
                scope = scope ?: CoroutineScope(coroutineContext),
                defaultExceptionsHandler = defaultExceptionsHandler,
                timeoutSeconds = timeoutSeconds,
                autoDisableWebhooks = autoDisableWebhooks,
                autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
                mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
                subcontextInitialAction = subcontextInitialAction,
                block = block,
            )
    }
}
