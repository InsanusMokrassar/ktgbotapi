package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.exceptions.GetUpdatesConflict
import dev.inmo.tgbotapi.bot.exceptions.RequestException
import dev.inmo.tgbotapi.extensions.utils.updates.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.extensions.utils.updates.lastUpdateIdentifier
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.*
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun TelegramBot.longPollingFlow(
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
): Flow<Update> = channelFlow {
    var lastUpdateIdentifier: UpdateIdentifier? = null

    while (isActive) {
        safely(
            { e ->
                exceptionsHandler ?.invoke(e)
                if (e is RequestException) {
                    delay(1000L)
                }
                if (e is GetUpdatesConflict && (exceptionsHandler == null || exceptionsHandler == defaultSafelyExceptionHandler)) {
                    println("Warning!!! Other bot with the same bot token requests updates with getUpdate in parallel")
                }
            }
        ) {
            val updates = execute(
                GetUpdates(
                    offset = lastUpdateIdentifier?.plus(1),
                    timeout = timeoutSeconds,
                    allowed_updates = allowedUpdates
                )
            ).let { originalUpdates ->
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
                if (originalUpdates.size == getUpdatesLimit.last && converted.last() is SentMediaGroupUpdate) {
                    converted - converted.last()
                } else {
                    converted
                }
            }

            safelyWithResult {
                for (update in updates) {
                    send(update)

                    lastUpdateIdentifier = update.lastUpdateIdentifier()
                }
            }.onFailure {
                cancel(it as? CancellationException ?: return@onFailure)
            }
        }
    }
}

fun TelegramBot.startGettingOfUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = longPollingFlow(timeoutSeconds, exceptionsHandler, allowedUpdates).subscribeSafely(
    scope,
    exceptionsHandler ?: defaultSafelyExceptionHandler,
    updatesReceiver
)

/**
 * @return [kotlinx.coroutines.flow.Flow] which will emit updates to the collector while they will be accumulated. Works
 * the same as [longPollingFlow], but it will cancel the flow after the first one [HttpRequestTimeoutException]
 */
fun TelegramBot.createAccumulatedUpdatesRetrieverFlow(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    allowedUpdates: List<String>? = null
): Flow<Update> = longPollingFlow(
    timeoutSeconds = 0,
    exceptionsHandler = {
        if (it is HttpRequestTimeoutException) {
            throw CancellationException("Cancel due to absence of new updates")
        } else {
            exceptionsHandler ?.invoke(it)
        }
    },
    allowedUpdates = allowedUpdates
).filter {
    !(it is InlineQueryUpdate && avoidInlineQueries || it is CallbackQueryUpdate && avoidCallbackQueries)
}

fun TelegramBot.retrieveAccumulatedUpdates(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = createAccumulatedUpdatesRetrieverFlow(
    avoidInlineQueries,
    avoidCallbackQueries,
    exceptionsHandler,
    allowedUpdates
).subscribeSafelyWithoutExceptions(
    scope.LinkedSupervisorScope()
) {
    updatesReceiver(it)
}

fun TelegramBot.retrieveAccumulatedUpdates(
    flowsUpdatesFilter: FlowsUpdatesFilter,
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null
) = retrieveAccumulatedUpdates(
    avoidInlineQueries,
    avoidCallbackQueries,
    scope,
    exceptionsHandler,
    flowsUpdatesFilter.allowedUpdates,
    flowsUpdatesFilter.asUpdateReceiver
)

suspend fun TelegramBot.flushAccumulatedUpdates(
    avoidInlineQueries: Boolean = false,
    avoidCallbackQueries: Boolean = false,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    allowedUpdates: List<String>? = null,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    updatesReceiver: UpdateReceiver<Update> = {}
) = retrieveAccumulatedUpdates(
    avoidInlineQueries,
    avoidCallbackQueries,
    scope,
    exceptionsHandler,
    allowedUpdates,
    updatesReceiver
).join()

/**
 * Will [startGettingOfUpdatesByLongPolling] using incoming [flowsUpdatesFilter]. It is assumed that you ALREADY CONFIGURE
 * all updates receivers, because this method will trigger getting of updates and.
 */
fun TelegramBot.longPolling(
    flowsUpdatesFilter: FlowsUpdatesFilter,
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null
): Job = flowsUpdatesFilter.run {
    startGettingOfUpdatesByLongPolling(timeoutSeconds, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

/**
 * Will enable [longPolling] by creating [FlowsUpdatesFilter] with [flowsUpdatesFilterUpdatesKeeperCount] as an argument
 * and applied [flowUpdatesPreset]. It is assumed that you WILL CONFIGURE all updates receivers in [flowUpdatesPreset],
 * because of after [flowUpdatesPreset] method calling will be triggered getting of updates.
 */
@Suppress("unused")
fun TelegramBot.longPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit
): Job = longPolling(FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply(flowUpdatesPreset), timeoutSeconds, scope, exceptionsHandler)

fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    updatesFilter: UpdatesFilter,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdatesByLongPolling(
    timeoutSeconds,
    scope,
    exceptionsHandler,
    updatesFilter.allowedUpdates,
    updatesFilter.asUpdateReceiver
)
