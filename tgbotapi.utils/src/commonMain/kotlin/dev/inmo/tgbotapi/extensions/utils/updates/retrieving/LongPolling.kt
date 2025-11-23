package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.exceptions.*
import dev.inmo.tgbotapi.extensions.utils.updates.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.requests.webhook.DeleteWebhook
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.*
import dev.inmo.tgbotapi.utils.isCausedUnresolvedAddressException
import dev.inmo.tgbotapi.utils.subscribeWithBotLogger
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Starts a long polling flow to receive updates continuously from the Telegram Bot API.
 *
 * This method retrieves updates from the bot, processes them, and emits them as a flow of `Update` objects.
 * It allows handling of updates with features like automatic webhook disabling, timeout exception skipping,
 * and media group handling with debounce time.
 *
 * @param exceptionsHandler Optional exception handler to manage exceptions that occur during the polling process.
 * @param autoDisableWebhooks Specifies whether to automatically disable existing webhooks before starting the long polling flow (default: `true`).
 * @param autoSkipTimeoutExceptions Defines if timeout-related exceptions should automatically be skipped during the polling (default: `true`).
 * @param mediaGroupsDebounceTimeMillis The debounce time in milliseconds for processing media group updates.
 *        If set to `null`, media group handling is disabled (default: `1000L`).
 * @param getUpdatesRequestCreator A function that creates a `GetUpdates` request for retrieving updates.
 *        This function accepts the identifier of the most recent update as an input and returns the new request.
 * @return A [Flow] of [Update] objects that represents the continuous stream of updates received.
 */
fun TelegramBot.longPollingFlow(
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    getUpdatesRequestCreator: (sinceUpdate: UpdateId?) -> GetUpdates
): Flow<Update> = channelFlow {
    if (autoDisableWebhooks) {
        runCatchingLogging(logger = Log) {
            execute(DeleteWebhook())
        }
    }

    val contextSafelyExceptionHandler = coroutineContext[ContextSafelyExceptionHandlerKey] ?.handler ?: defaultSafelyExceptionHandler
    val contextToWork = if (!autoSkipTimeoutExceptions) {
        coroutineContext
    } else {
        coroutineContext + ContextSafelyExceptionHandler { e ->
            if (e is HttpRequestTimeoutException || (e is CommonBotException && e.cause is HttpRequestTimeoutException)) {
                return@ContextSafelyExceptionHandler
            } else {
                contextSafelyExceptionHandler(e)
            }
        }
    }

    var lastUpdateIdentifier: UpdateId? = null

    val updatesHandler: (suspend (List<Update>) -> Unit) = if (mediaGroupsDebounceTimeMillis != null) {
        val scope = CoroutineScope(contextToWork)
        val updatesReceiver = scope.updateHandlerWithMediaGroupsAdaptation(
            {
                withContext(contextToWork) {
                    send(it)
                }
            },
            mediaGroupsDebounceTimeMillis,
            logger = Log
        );
        { originalUpdates: List<Update> ->
            originalUpdates.forEach {
                updatesReceiver(it)
                lastUpdateIdentifier = maxOf(lastUpdateIdentifier ?: it.updateId, it.updateId)
            }
        }
    } else {
        { originalUpdates: List<Update> ->
            val converted = originalUpdates.convertWithMediaGroupUpdates()

            /**
             * Dirty hack for cases when the media group was retrieved not fully:
             *
             * We are throw out the last media group and will reretrieve it again in the next get updates
             * and it will guarantee that it is full
             */
            /**
             * Dirty hack for cases when the media group was retrieved not fully:
             *
             * We are throw out the last media group and will reretrieve it again in the next get updates
             * and it will guarantee that it is full
             */
            val updates = if (
                originalUpdates.size == getUpdatesLimit.last
                && ((converted.last() as? BaseSentMessageUpdate)?.data as? CommonMessage<*>)?.content is MediaGroupContent<*>
            ) {
                converted - converted.last()
            } else {
                converted
            }

            runCatching {
                for (update in updates) {
                    send(update)

                    if (update.updateId.long > -1) {
                        lastUpdateIdentifier = update.updateId
                    }
                }
            }
        }
    }

    withContext(contextToWork) {
        while (isActive) {
            runCatchingLogging(logger = Log) {
                execute(
                    getUpdatesRequestCreator(lastUpdateIdentifier ?.plus(1))
                ).let { originalUpdates ->
                    updatesHandler(originalUpdates)
                }
            }.onFailure { e ->
                runCatchingLogging(logger = Log) {
                    val isHttpRequestTimeoutException =
                        e is ConnectTimeoutException || e is HttpRequestTimeoutException || (e is CommonBotException && e.cause is HttpRequestTimeoutException)
                    if (isHttpRequestTimeoutException && autoSkipTimeoutExceptions) {
                        return@onFailure
                    }

                    exceptionsHandler?.invoke(e)

                    // It seems some problems with internet connection. See https://github.com/InsanusMokrassar/ktgbotapi/issues/989
                    val isUnresolvedAddressException = e.isCausedUnresolvedAddressException()
                    if (e is RequestException || isUnresolvedAddressException) {
                        delay(1000L)
                    }
                }
            }
        }
    }
}

/**
 * Initiates a long polling flow for receiving updates continuously from the Telegram Bot API.
 * This method provides a customized way to handle the retrieval of updates with options
 * to configure timeouts, update types, exception handling, and media group processing.
 *
 * @param timeoutSeconds The maximum time in seconds for the server to wait for available updates
 * before responding (default: `30`).
 * @param exceptionsHandler An optional exception handler for managing exceptions that occur during the
 * long polling process. If not provided, exceptions will pass through unhandled.
 * @param allowedUpdates A list of the update types to retrieve. By default, retrieves all possible
 * update types as defined in `ALL_UPDATES_LIST`.
 * @param autoDisableWebhooks Whether the current webhook should be disabled automatically before starting the
 * long polling flow (default: `true`).
 * @param autoSkipTimeoutExceptions Determines if timeout-related exceptions should be automatically skipped
 * during the polling process (default: `true`).
 * @param mediaGroupsDebounceTimeMillis The debounce time in milliseconds for processing updates containing
 * media groups. If set to `null`, media group handling is disabled (default: `1000L`).
 * @return A Flow that emits Update objects representing the updates fetched from the Telegram Bot API.
 */
fun TelegramBot.longPollingFlow(
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
): Flow<Update> = longPollingFlow(
    exceptionsHandler = exceptionsHandler,
    autoDisableWebhooks = autoDisableWebhooks,
    autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
    mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
) {
    GetUpdates(
        offset = it,
        timeout = timeoutSeconds,
        allowed_updates = allowedUpdates
    )
}

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun TelegramBot.startGettingOfUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    updatesReceiver: UpdateReceiver<Update>
): Job = longPollingFlow(
    timeoutSeconds = timeoutSeconds,
    exceptionsHandler = exceptionsHandler ?: defaultSafelyExceptionHandler,
    allowedUpdates = allowedUpdates,
    autoDisableWebhooks = autoDisableWebhooks,
    autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
    mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis
).subscribeWithBotLogger(
    scope = scope,
    block = updatesReceiver
)

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 *
 * @return [kotlinx.coroutines.flow.Flow] which will emit updates to the collector while they will be accumulated. Works
 * the same as [longPollingFlow], but it will cancel the flow after the first one [HttpRequestTimeoutException]
 */
fun TelegramBot.createAccumulatedUpdatesRetrieverFlow(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    autoDisableWebhooks: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
): Flow<Update> = longPollingFlow(
    timeoutSeconds = 0,
    exceptionsHandler = {
        when {
            it is HttpRequestTimeoutException ||
            (it is CommonBotException && it.cause is HttpRequestTimeoutException) -> throw CancellationException("Cancel due to absence of new updates")
            else -> exceptionsHandler ?.invoke(it)
        }
    },
    allowedUpdates = allowedUpdates,
    autoDisableWebhooks = autoDisableWebhooks,
    autoSkipTimeoutExceptions = false,
    mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis
).filter {
    !(it is InlineQueryUpdate && avoidInlineQueries || it is CallbackQueryUpdate && avoidCallbackQueries)
}

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun TelegramBot.retrieveAccumulatedUpdates(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    autoDisableWebhooks: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    updatesReceiver: UpdateReceiver<Update>
): Job = createAccumulatedUpdatesRetrieverFlow(
    avoidInlineQueries,
    avoidCallbackQueries,
    exceptionsHandler,
    allowedUpdates,
    autoDisableWebhooks,
    mediaGroupsDebounceTimeMillis
).subscribeWithBotLogger(
    scope.LinkedSupervisorScope(),
) {
    updatesReceiver(it)
}

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun TelegramBot.retrieveAccumulatedUpdates(
    flowsUpdatesFilter: FlowsUpdatesFilter,
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    autoDisableWebhooks: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    exceptionsHandler: ExceptionHandler<Unit>? = null
) = retrieveAccumulatedUpdates(
    avoidInlineQueries,
    avoidCallbackQueries,
    scope,
    exceptionsHandler,
    flowsUpdatesFilter.allowedUpdates,
    autoDisableWebhooks,
    mediaGroupsDebounceTimeMillis,
    flowsUpdatesFilter.asUpdateReceiver
)

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
suspend fun TelegramBot.flushAccumulatedUpdates(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    autoDisableWebhooks: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    updatesReceiver: UpdateReceiver<Update> = {}
) = retrieveAccumulatedUpdates(
    avoidInlineQueries,
    avoidCallbackQueries,
    scope,
    exceptionsHandler,
    allowedUpdates,
    autoDisableWebhooks,
    mediaGroupsDebounceTimeMillis,
    updatesReceiver
).join()

/**
 * Will [startGettingOfUpdatesByLongPolling] using incoming [updatesFilter]. It is assumed that you ALREADY CONFIGURE
 * all updates receivers, because this method will trigger getting of updates and.
 *
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun TelegramBot.longPolling(
    updatesFilter: UpdatesFilter,
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    exceptionsHandler: ExceptionHandler<Unit>? = null
): Job = updatesFilter.run {
    startGettingOfUpdatesByLongPolling(
        timeoutSeconds = timeoutSeconds,
        scope = scope,
        exceptionsHandler = exceptionsHandler,
        allowedUpdates = allowedUpdates,
        autoDisableWebhooks = autoDisableWebhooks,
        autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
        mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
        updatesReceiver = asUpdateReceiver
    )
}

/**
 * Will enable [longPolling] by creating [FlowsUpdatesFilter] with [flowsUpdatesFilterUpdatesKeeperCount] as an argument
 * and applied [flowUpdatesPreset]. It is assumed that you WILL CONFIGURE all updates receivers in [flowUpdatesPreset],
 * because of after [flowUpdatesPreset] method calling will be triggered getting of updates.
 *
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
@Suppress("unused")
fun TelegramBot.longPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit
): Job = longPolling(FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply(flowUpdatesPreset), timeoutSeconds, scope, autoDisableWebhooks, autoSkipTimeoutExceptions, mediaGroupsDebounceTimeMillis, exceptionsHandler)

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    updatesFilter: UpdatesFilter,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    autoDisableWebhooks: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    autoSkipTimeoutExceptions: Boolean = true,
): Job = startGettingOfUpdatesByLongPolling(
    timeoutSeconds,
    scope,
    exceptionsHandler,
    updatesFilter.allowedUpdates,
    autoDisableWebhooks,
    autoSkipTimeoutExceptions,
    mediaGroupsDebounceTimeMillis,
    updatesFilter.asUpdateReceiver
)
