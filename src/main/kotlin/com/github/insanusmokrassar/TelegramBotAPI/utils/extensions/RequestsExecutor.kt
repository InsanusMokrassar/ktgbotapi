package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.requests.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.Response
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import kotlinx.coroutines.*

typealias UpdateReceiver<T> = suspend (T) -> Unit

fun RequestsExecutor.startGettingOfUpdates(
    requestsDelayMillis: Long = 1000,
    scope: CoroutineScope = GlobalScope,
    allowedUpdates: List<String>? = null,
    block: UpdateReceiver<Any>
): Job {
    return scope.launch {
        var lastHandledUpdate: UpdateIdentifier = 0L
        while (isActive) {
            delay(requestsDelayMillis)
            try {
                val updates = execute(
                    GetUpdates(
                        lastHandledUpdate + 1,
                        allowed_updates = allowedUpdates
                    )
                )

                val adaptedUpdates = mutableListOf<Any>()
                var mediaGroup: MutableList<Update>? = null

                fun pushMediaGroup() {
                    mediaGroup ?.also {
                        adaptedUpdates.add(it)
                        mediaGroup = null
                    }
                }

                updates.map {
                    it.asUpdate
                }.forEach { update ->
                    val data = update.data
                    if (data is MediaGroupMessage) {
                        mediaGroup ?.let {
                            val message = it.first().data as MediaGroupMessage
                            if (message.mediaGroupId == data.mediaGroupId) {
                                it.add(update)
                            } else {
                                null
                            }
                        } ?: data.also {
                            pushMediaGroup()
                            mediaGroup = mutableListOf()
                            mediaGroup ?.add(update)
                        }
                    } else {
                        pushMediaGroup()
                        adaptedUpdates.add(update)
                    }
                }

                mediaGroup ?.also {
                    adaptedUpdates.add(it)
                    mediaGroup = null
                }

                for (update in adaptedUpdates) {

                    try {
                        block(update)
                        lastHandledUpdate = when (update) {
                            is Update -> update.updateId
                            is List<*> -> (update.last() as? Update) ?.updateId ?: throw IllegalStateException(
                                "Found non-updates oriented list"
                            )
                            else -> throw IllegalStateException(
                                "Unknown type of data"
                            )
                        }
                    } catch (e: Exception) {
                        // TODO:: add exception handling
                        e.printStackTrace()
                        break
                    }
                }
            } catch (e: Exception) {
                // TODO:: add exception handling
                e.printStackTrace()
            }
        }
    }
}

fun RequestsExecutor.startGettingOfUpdates(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    messageMediaGroupCallback: UpdateReceiver<List<MessageUpdate>>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    editedMessageMediaGroupCallback: UpdateReceiver<List<EditMessageUpdate>>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    channelPostMediaGroupCallback: UpdateReceiver<List<ChannelPostUpdate>>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    editedChannelPostMediaGroupCallback: UpdateReceiver<List<EditChannelPostUpdate>>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    requestsDelayMillis: Long = 1000,
    scope: CoroutineScope = GlobalScope
): Job {
    val filter = UpdatesFilter(
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
        preCheckoutQueryCallback
    )
    return startGettingOfUpdates(
        requestsDelayMillis,
        scope,
        filter.allowedUpdates,
        filter.asUpdateReceiver
    )
}

fun RequestsExecutor.startGettingOfUpdates(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    mediaGroupCallback: UpdateReceiver<List<BaseMessageUpdate>>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    requestsDelayMillis: Long = 1000,
    scope: CoroutineScope = GlobalScope
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
    requestsDelayMillis = requestsDelayMillis,
    scope = scope
)

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    onFail: (suspend (Response<*>) -> Unit)? = null,
    scope: CoroutineScope = GlobalScope,
    onSuccess: (suspend (T) -> Unit)? = null
): Job {
    return scope.launch {
        try {
            val result = execute(request)
            onSuccess ?.invoke(result)
        } catch (e: RequestException) {
            onFail ?.invoke(e.response)
        }
    }
}

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    scope: CoroutineScope = GlobalScope
): Deferred<T> {
    return scope.async { execute(request) }
}

suspend fun <T: Any> RequestsExecutor.executeUnsafe(
    request: Request<T>,
    retries: Int = 0,
    retriesDelay: Long = 1000L
): T? {
    var leftRetries = retries
    while(true) {
        try {
            return execute(request)
        } catch (e: RequestException) {
            if (leftRetries > 0) {
                leftRetries--
                delay(retriesDelay)
            } else {
                return null
            }
        }
    }
}
