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
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun TelegramBot.longPollingFlow(
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = ALL_UPDATES_LIST,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
): Flow<Update> = channelFlow {
    if (autoDisableWebhooks) {
        runCatchingSafely {
            execute(DeleteWebhook())
        }
    }

    val contextSafelyExceptionHandler = coroutineContext[ContextSafelyExceptionHandlerKey]
    val contextToWork = if (contextSafelyExceptionHandler == null || !autoSkipTimeoutExceptions) {
        coroutineContext
    } else {
        coroutineContext + ContextSafelyExceptionHandler { e ->
            if (e is HttpRequestTimeoutException || (e is CommonBotException && e.cause is HttpRequestTimeoutException)) {
                return@ContextSafelyExceptionHandler
            } else {
                contextSafelyExceptionHandler.handler(e)
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
            mediaGroupsDebounceTimeMillis
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
            val updates = if (
                originalUpdates.size == getUpdatesLimit.last
                && ((converted.last() as? BaseSentMessageUpdate) ?.data as? CommonMessage<*>) ?.content is MediaGroupContent<*>
            ) {
                converted - converted.last()
            } else {
                converted
            }

            safelyWithResult {
                for (update in updates) {
                    send(update)

                    if (update.updateId.long > -1) {
                        lastUpdateIdentifier = update.updateId
                    }
                }
            }.onFailure {
                cancel(it as? CancellationException ?: return@onFailure)
            }
        }
    }

    withContext(contextToWork) {
        while (isActive) {
            safely(
                { e ->
                    val isHttpRequestTimeoutException = e is HttpRequestTimeoutException || (e is CommonBotException && e.cause is HttpRequestTimeoutException)
                    if (isHttpRequestTimeoutException && autoSkipTimeoutExceptions) {
                        return@safely
                    }
                    exceptionsHandler ?.invoke(e)
                    if (e is RequestException) {
                        delay(1000L)
                    }
                }
            ) {
                execute(
                    GetUpdates(
                        offset = lastUpdateIdentifier?.plus(1),
                        timeout = timeoutSeconds,
                        allowed_updates = allowedUpdates
                    )
                ).let { originalUpdates ->
                    updatesHandler(originalUpdates)
                }
            }
        }
    }
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
    exceptionsHandler = exceptionsHandler,
    allowedUpdates = allowedUpdates,
    autoDisableWebhooks = autoDisableWebhooks,
    autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
    mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis
).subscribeSafely(
    scope,
    exceptionsHandler ?: defaultSafelyExceptionHandler,
    updatesReceiver
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
).subscribeSafelyWithoutExceptions(
    scope.LinkedSupervisorScope()
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
