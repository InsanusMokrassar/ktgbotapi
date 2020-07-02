package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.updates

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils.convertWithMediaGroupUpdates
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils.lastUpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.getUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import kotlinx.coroutines.*

@Deprecated("Replaced and renamed in TelegramBotAPI-extensions-utils")
fun RequestsExecutor.startGettingOfUpdates(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (ExceptionHandler<Unit>)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = scope.launch {
    var lastUpdateIdentifier: UpdateIdentifier? = null

    while (isActive) {
        handleSafely(
            { e ->
                exceptionsHandler ?.invoke(e)
                if (e is RequestException) {
                    delay(1000L)
                }
            }
        ) {
            val updates = getUpdates(
                offset = lastUpdateIdentifier?.plus(1),
                timeout = timeoutSeconds,
                allowed_updates = allowedUpdates
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

            handleSafely {
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
@Deprecated("Replaced and renamed in TelegramBotAPI-extensions-utils")
fun RequestsExecutor.startGettingFlowsUpdates(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    flowsUpdatesFilterUpdatesKeeperCount: Int = 100,
    flowUpdatesPreset: FlowsUpdatesFilter.() -> Unit = {}
): FlowsUpdatesFilter = FlowsUpdatesFilter(flowsUpdatesFilterUpdatesKeeperCount).apply {
    flowUpdatesPreset()
    startGettingOfUpdates(timeoutSeconds, scope, exceptionsHandler, allowedUpdates, asUpdateReceiver)
}

@Deprecated("Replaced and renamed in TelegramBotAPI-extensions-utils")
fun RequestsExecutor.startGettingOfUpdates(
    updatesFilter: UpdatesFilter,
    timeoutSeconds: Seconds = 30,
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdates(
    timeoutSeconds,
    scope,
    exceptionsHandler,
    updatesFilter.allowedUpdates,
    updatesFilter.asUpdateReceiver
)

@Deprecated("Replaced and renamed in TelegramBotAPI-extensions-utils")
fun RequestsExecutor.startGettingOfUpdates(
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
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    scope: CoroutineScope = GlobalScope
): Job {
    return startGettingOfUpdates(
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
@Deprecated("Replaced and renamed in TelegramBotAPI-extensions-utils")
fun RequestsExecutor.startGettingOfUpdates(
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
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
): Job = startGettingOfUpdates(
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
