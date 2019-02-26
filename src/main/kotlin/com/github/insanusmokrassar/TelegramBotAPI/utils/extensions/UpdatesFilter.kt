package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.requests.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

data class UpdatesFilter(
    private val messageCallback: UpdateReceiver<MessageUpdate>? = null,
    private val messageMediaGroupCallback: UpdateReceiver<List<MessageUpdate>>? = null,
    private val editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    private val editedMessageMediaGroupCallback: UpdateReceiver<List<EditMessageUpdate>>? = null,
    private val channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    private val channelPostMediaGroupCallback: UpdateReceiver<List<ChannelPostUpdate>>? = null,
    private val editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    private val editedChannelPostMediaGroupCallback: UpdateReceiver<List<EditChannelPostUpdate>>? = null,
    private val chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    private val inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    private val callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    private val shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    private val preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null
) {
    val asUpdateReceiver: UpdateReceiver<Any> = this::invoke
    val allowedUpdates = listOfNotNull(
        (messageCallback ?: messageMediaGroupCallback) ?.let { UPDATE_MESSAGE },
        (editedMessageCallback ?: editedMessageMediaGroupCallback) ?.let { UPDATE_EDITED_MESSAGE },
        (channelPostCallback ?: channelPostMediaGroupCallback) ?.let { UPDATE_CHANNEL_POST },
        (editedChannelPostCallback ?: editedChannelPostMediaGroupCallback) ?.let { UPDATE_EDITED_CHANNEL_POST },
        chosenInlineResultCallback ?.let { UPDATE_CHOSEN_INLINE_RESULT },
        inlineQueryCallback ?.let { UPDATE_INLINE_QUERY },
        callbackQueryCallback ?.let { UPDATE_CALLBACK_QUERY },
        shippingQueryCallback ?.let { UPDATE_SHIPPING_QUERY },
        preCheckoutQueryCallback ?.let { UPDATE_PRE_CHECKOUT_QUERY }
    )

    suspend fun invoke(update: Any) {
        when (update) {
            is MessageUpdate -> messageCallback ?.invoke(update)
            is List<*> -> when (update.firstOrNull()) {
                is MessageUpdate -> update.mapNotNull { it as? MessageUpdate }.let { mappedList ->
                    messageMediaGroupCallback ?.also { receiver ->
                        receiver(mappedList)
                    } ?: messageCallback ?.also { receiver ->
                        mappedList.forEach { receiver(it) }
                    }
                }
                is EditMessageUpdate -> update.mapNotNull { it as? EditMessageUpdate }.let { mappedList ->
                    editedMessageMediaGroupCallback ?.also { receiver ->
                        receiver(mappedList)
                    } ?: editedMessageCallback ?.also { receiver ->
                        mappedList.forEach { receiver(it) }
                    }
                }
                is ChannelPostUpdate -> update.mapNotNull { it as? ChannelPostUpdate }.let { mappedList ->
                    channelPostMediaGroupCallback ?.also { receiver ->
                        receiver(mappedList)
                    } ?: channelPostCallback ?.also { receiver ->
                        mappedList.forEach { receiver(it) }
                    }
                }
                is EditChannelPostUpdate -> update.mapNotNull { it as? EditChannelPostUpdate }.let { mappedList ->
                    editedChannelPostMediaGroupCallback ?.also { receiver ->
                        receiver(mappedList)
                    } ?: editedChannelPostCallback ?.also { receiver ->
                        mappedList.forEach { receiver(it) }
                    }
                }
            }
            is EditMessageUpdate -> editedMessageCallback ?.invoke(update)
            is ChannelPostUpdate -> channelPostCallback ?.invoke(update)
            is EditChannelPostUpdate -> editedChannelPostCallback ?.invoke(update)
            is ChosenInlineResultUpdate -> chosenInlineResultCallback ?.invoke(update)
            is InlineQueryUpdate -> inlineQueryCallback ?.invoke(update)
            is CallbackQueryUpdate -> callbackQueryCallback ?.invoke(update)
            is ShippingQueryUpdate -> shippingQueryCallback ?.invoke(update)
            is PreCheckoutQueryUpdate -> preCheckoutQueryCallback ?.invoke(update)
        }
    }
}

fun createSimpleUpdateFilter(
    messageCallback: UpdateReceiver<MessageUpdate>? = null,
    mediaGroupCallback: UpdateReceiver<List<BaseMessageUpdate>>? = null,
    editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null
): UpdatesFilter = UpdatesFilter(
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
    preCheckoutQueryCallback = preCheckoutQueryCallback
)
