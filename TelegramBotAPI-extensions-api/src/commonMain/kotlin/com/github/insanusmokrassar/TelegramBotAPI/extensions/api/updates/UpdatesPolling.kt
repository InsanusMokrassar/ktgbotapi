package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.updates

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils.convertWithMediaGroupUpdates
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils.lastUpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.getUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.Seconds
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.*
import io.ktor.client.features.HttpRequestTimeoutException
import kotlinx.coroutines.*

fun RequestsExecutor.startGettingOfUpdates(
    timeoutSeconds: Seconds = 30,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    allowedUpdates: List<String>? = null,
    updatesReceiver: UpdateReceiver<Update>
): Job = scope.launch {
    var lastUpdateIdentifier: UpdateIdentifier? = null

    while (isActive) {
        try {
            supervisorScope {
                val updates = getUpdates(
                    offset = lastUpdateIdentifier?.plus(1),
                    timeout = timeoutSeconds,
                    allowed_updates = allowedUpdates
                ).convertWithMediaGroupUpdates()

                supervisorScope {
                    for (update in updates) {
                        updatesReceiver(update)

                        lastUpdateIdentifier = update.lastUpdateIdentifier()
                    }
                }
            }
        } catch (e: HttpRequestTimeoutException) {
            exceptionsHandler ?.invoke(e) // it is ok due to mechanism of long polling
        } catch (e: RequestException) {
            exceptionsHandler ?.invoke(e) // it is not ok, but in most cases it will mean that there is some limit for requests count
            delay(1000L)
        } catch (e: Exception) {
            exceptionsHandler ?.invoke(e)
        }
    }
}

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
