package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.RequestsExecutor
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
import kotlinx.coroutines.*

fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = scope.launch {
    var lastUpdateIdentifier: UpdateIdentifier? = null

    while (isActive) {
        safely(
            { e ->
                exceptionsHandler ?.invoke(e)
                if (e is RequestException) {
                    delay(1000L)
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
                if (originalUpdates.size == getUpdatesLimit.last && converted.last() is SentMediaGroupUpdate) {
                    converted - converted.last()
                } else {
                    converted
                }
            }

            safely {
                for (update in updates) {
                    updatesReceiver(update)

                    lastUpdateIdentifier = update.lastUpdateIdentifier()
                }
            }
        }
    }
}

/**
 * This method will create a new one [FlowsUpdatesFilter]. This method could be unsafe due to the fact that it will start
 * getting updates IMMEDIATELY. That means that your bot will be able to skip some of them until you will call
 * [kotlinx.coroutines.flow.Flow.collect] on one of [FlowsUpdatesFilter] flows. To avoid it, you can pass
 * [flowUpdatesPreset] lambda - it will be called BEFORE starting updates getting
 */
@FlowPreview
@PreviewFeature
@Suppress("unused")
fun RequestsExecutor.startGettingFlowsUpdatesByLongPolling(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit = {}
): FlowsUpdatesFilter = FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply {
    flowUpdatesPreset()
    startGettingOfUpdatesByLongPolling(timeoutSeconds, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

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

fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    messageMediaGroupCallback: UpdateReceiver<MessageMediaGroupUpdate>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    editedMessageMediaGroupCallback: UpdateReceiver<EditMessageMediaGroupUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    channelPostMediaGroupCallback: UpdateReceiver<ChannelPostMediaGroupUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    editedChannelPostMediaGroupCallback: UpdateReceiver<EditChannelPostMediaGroupUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    pollCallback: UpdateReceiver<PollUpdate>? = null,
    pollAnswerCallback: UpdateReceiver<PollAnswerUpdate>? = null,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job {
    return startGettingOfUpdatesByLongPolling(
        SimpleUpdatesFilter(
            messageCallback,
            messageMediaGroupCallback,
            editedMessageCallback,
            editedMessageMediaGroupCallback,
            channelPostCallback,
            channelPostMediaGroupCallback,
            editedChannelPostCallback,
            editedChannelPostMediaGroupCallback,
            chosenInlineResultCallback,
            inlineQueryCallback,
            callbackQueryCallback,
            shippingQueryCallback,
            preCheckoutQueryCallback,
            pollCallback,
            pollAnswerCallback
        ),
        timeoutSeconds,
        exceptionsHandler,
        scope
    )
}

@Suppress("unused")
fun RequestsExecutor.startGettingOfUpdatesByLongPolling(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    mediaGroupCallback: UpdateReceiver<MediaGroupUpdate>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    pollCallback: UpdateReceiver<PollUpdate>? = null,
    pollAnswerCallback: UpdateReceiver<PollAnswerUpdate>? = null,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdatesByLongPolling(
    messageCallback = messageCallback,
    messageMediaGroupCallback = mediaGroupCallback,
    editedMessageCallback = editedMessageCallback,
    editedMessageMediaGroupCallback = mediaGroupCallback,
    channelPostCallback = channelPostCallback,
    channelPostMediaGroupCallback = mediaGroupCallback,
    editedChannelPostCallback = editedChannelPostCallback,
    editedChannelPostMediaGroupCallback = mediaGroupCallback,
    chosenInlineResultCallback = chosenInlineResultCallback,
    inlineQueryCallback = inlineQueryCallback,
    callbackQueryCallback = callbackQueryCallback,
    shippingQueryCallback = shippingQueryCallback,
    preCheckoutQueryCallback = preCheckoutQueryCallback,
    pollCallback = pollCallback,
    pollAnswerCallback = pollAnswerCallback,
    timeoutSeconds = timeoutSeconds,
    exceptionsHandler = exceptionsHandler,
    scope = scope
)

