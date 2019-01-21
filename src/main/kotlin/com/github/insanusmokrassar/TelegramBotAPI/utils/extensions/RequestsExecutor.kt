package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.ResponseParameters
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
): Job {
    return startGettingOfUpdates(
        requestsDelayMillis,
        scope,
        listOfNotNull(
            messageCallback ?.let { UPDATE_MESSAGE },
            editedMessageCallback ?.let { UPDATE_EDITED_MESSAGE },
            channelPostCallback ?.let { UPDATE_CHANNEL_POST },
            editedChannelPostCallback ?.let { UPDATE_EDITED_CHANNEL_POST },
            chosenInlineResultCallback ?.let { UPDATE_CHOSEN_INLINE_RESULT },
            inlineQueryCallback ?.let { UPDATE_INLINE_QUERY },
            callbackQueryCallback ?.let { UPDATE_CALLBACK_QUERY },
            shippingQueryCallback ?.let { UPDATE_SHIPPING_QUERY },
            preCheckoutQueryCallback ?.let { UPDATE_PRE_CHECKOUT_QUERY }
        )
    ) {
        when (it) {
            is MessageUpdate -> messageCallback ?.invoke(it)
            is List<*> -> mediaGroupCallback ?.invoke(
                it.mapNotNull {
                    it as? BaseMessageUpdate
                }
            )
            is EditMessageUpdate -> editedMessageCallback ?.invoke(it)
            is ChannelPostUpdate -> channelPostCallback ?.invoke(it)
            is EditChannelPostUpdate -> editedChannelPostCallback ?.invoke(it)
            is ChosenInlineResultUpdate -> chosenInlineResultCallback ?.invoke(it)
            is InlineQueryUpdate -> inlineQueryCallback ?.invoke(it)
            is CallbackQueryUpdate -> callbackQueryCallback ?.invoke(it)
            is ShippingQueryUpdate -> shippingQueryCallback ?.invoke(it)
            is PreCheckoutQueryUpdate -> preCheckoutQueryCallback ?.invoke(it)
        }
    }
}

fun <T: Any> RequestsExecutor.executeAsync(
    request: Request<T>,
    onFail: (suspend (ResponseParameters<*>) -> Unit)? = null,
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
): Job {
    return scope.async { execute(request) }
}

suspend fun <T: Any> RequestsExecutor.executeUnsafe(
    request: Request<T>
): T? {
    return try {
        execute(request)
    } catch (e: RequestException) {
        null
    }
}
