package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestException
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.CallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.ChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InlineQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.ResponseParameters
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.PreCheckoutQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.ShippingQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import kotlinx.coroutines.*

typealias UpdateReceiver<T> = suspend Update<T>.() -> Unit

fun RequestsExecutor.startGettingOfUpdates(
    requestsDelayMillis: Long = 1000,
    scope: CoroutineScope = GlobalScope,
    allowedUpdates: List<String>? = null,
    block: UpdateReceiver<*>
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

                for (rawUpdate in updates) {
                    try {
                        val update = rawUpdate.asUpdate
                        block(update)
                        lastHandledUpdate = update.updateId
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
    messageCallback: UpdateReceiver<Message>? = null,
    editedMessageCallback: UpdateReceiver<Message>? = null,
    channelPostCallback: UpdateReceiver<Message>? = null,
    editedChannelPostCallback: UpdateReceiver<Message>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResult>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQuery>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQuery>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQuery>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQuery>? = null,
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
        when (this) {
            is MessageUpdate -> messageCallback ?.invoke(this)
            is EditMessageUpdate -> editedMessageCallback ?.invoke(this)
            is ChannelPostUpdate -> channelPostCallback ?.invoke(this)
            is EditChannelPostUpdate -> editedChannelPostCallback ?.invoke(this)
            is ChosenInlineResultUpdate -> chosenInlineResultCallback ?.invoke(this)
            is InlineQueryUpdate -> inlineQueryCallback ?.invoke(this)
            is CallbackQueryUpdate -> callbackQueryCallback ?.invoke(this)
            is ShippingQueryUpdate -> shippingQueryCallback ?.invoke(this)
            is PreCheckoutQueryUpdate -> preCheckoutQueryCallback ?.invoke(this)
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
