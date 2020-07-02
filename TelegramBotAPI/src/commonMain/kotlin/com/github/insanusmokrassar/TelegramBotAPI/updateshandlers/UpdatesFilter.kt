package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UnknownUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

typealias UpdateReceiver<T> = suspend (T) -> Unit

interface UpdatesFilter {
    val asUpdateReceiver: UpdateReceiver<Update>
    val allowedUpdates: List<String>
}

data class SimpleUpdatesFilter(
    private val messageCallback: UpdateReceiver<MessageUpdate>? = null,
    private val messageMediaGroupCallback: UpdateReceiver<MessageMediaGroupUpdate>? = null,
    private val editedMessageCallback: UpdateReceiver<EditMessageUpdate>? = null,
    private val editedMessageMediaGroupCallback: UpdateReceiver<EditMessageMediaGroupUpdate>? = null,
    private val channelPostCallback: UpdateReceiver<ChannelPostUpdate>? = null,
    private val channelPostMediaGroupCallback: UpdateReceiver<ChannelPostMediaGroupUpdate>? = null,
    private val editedChannelPostCallback: UpdateReceiver<EditChannelPostUpdate>? = null,
    private val editedChannelPostMediaGroupCallback: UpdateReceiver<EditChannelPostMediaGroupUpdate>? = null,
    private val chosenInlineResultCallback: UpdateReceiver<ChosenInlineResultUpdate>? = null,
    private val inlineQueryCallback: UpdateReceiver<InlineQueryUpdate>? = null,
    private val callbackQueryCallback: UpdateReceiver<CallbackQueryUpdate>? = null,
    private val shippingQueryCallback: UpdateReceiver<ShippingQueryUpdate>? = null,
    private val preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null,
    private val pollUpdateCallback: UpdateReceiver<PollUpdate>? = null,
    private val pollAnswerUpdateCallback: UpdateReceiver<PollAnswerUpdate>? = null,
    private val unknownUpdateTypeCallback: UpdateReceiver<UnknownUpdate>? = null
) : UpdatesFilter {
    override val asUpdateReceiver: UpdateReceiver<Update> = this::invoke
    override val allowedUpdates = listOfNotNull(
        (messageCallback ?: messageMediaGroupCallback) ?.let { UPDATE_MESSAGE },
        (editedMessageCallback ?: editedMessageMediaGroupCallback) ?.let { UPDATE_EDITED_MESSAGE },
        (channelPostCallback ?: channelPostMediaGroupCallback) ?.let { UPDATE_CHANNEL_POST },
        (editedChannelPostCallback ?: editedChannelPostMediaGroupCallback) ?.let { UPDATE_EDITED_CHANNEL_POST },
        chosenInlineResultCallback ?.let { UPDATE_CHOSEN_INLINE_RESULT },
        inlineQueryCallback ?.let { UPDATE_INLINE_QUERY },
        callbackQueryCallback ?.let { UPDATE_CALLBACK_QUERY },
        shippingQueryCallback ?.let { UPDATE_SHIPPING_QUERY },
        preCheckoutQueryCallback ?.let { UPDATE_PRE_CHECKOUT_QUERY },
        pollUpdateCallback ?.let { UPDATE_POLL },
        pollAnswerUpdateCallback ?.let { UPDATE_POLL_ANSWER }
    )

    suspend fun invoke(update: Update) {
        when (update) {
            is MessageUpdate -> messageCallback ?.invoke(update)
            is MessageMediaGroupUpdate -> messageMediaGroupCallback ?.also { receiver ->
                receiver(update)
            } ?: messageCallback ?.also { receiver ->
                update.origins.mapNotNull { it as? MessageUpdate }.forEach {
                    receiver(it)
                }
            }
            is EditMessageUpdate -> editedMessageCallback ?.invoke(update)
            is EditMessageMediaGroupUpdate -> editedMessageMediaGroupCallback ?.also { receiver ->
                receiver(update)
            } ?: editedMessageCallback ?.also { receiver ->
                receiver(update.origin)
            }
            is ChannelPostUpdate -> channelPostCallback ?.invoke(update)
            is ChannelPostMediaGroupUpdate -> channelPostMediaGroupCallback ?.also { receiver ->
                receiver(update)
            } ?: channelPostCallback ?.also { receiver ->
                update.origins.mapNotNull { it as? ChannelPostUpdate }.forEach {
                    receiver(it)
                }
            }
            is EditChannelPostUpdate -> editedChannelPostCallback ?.invoke(update)
            is EditChannelPostMediaGroupUpdate -> editedChannelPostMediaGroupCallback ?.also { receiver ->
                receiver(update)
            } ?: editedChannelPostCallback ?.also { receiver ->
                receiver(update.origin)
            }
            is ChosenInlineResultUpdate -> chosenInlineResultCallback ?.invoke(update)
            is InlineQueryUpdate -> inlineQueryCallback ?.invoke(update)
            is CallbackQueryUpdate -> callbackQueryCallback ?.invoke(update)
            is ShippingQueryUpdate -> shippingQueryCallback ?.invoke(update)
            is PreCheckoutQueryUpdate -> preCheckoutQueryCallback ?.invoke(update)
            is PollUpdate -> pollUpdateCallback ?.invoke(update)
            is PollAnswerUpdate -> pollAnswerUpdateCallback ?.invoke(update)
            is UnknownUpdate -> unknownUpdateTypeCallback ?.invoke(update)
        }
    }
}

fun createSimpleUpdateFilter(
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
    unknownCallback: UpdateReceiver<UnknownUpdate>? = null
): UpdatesFilter = SimpleUpdatesFilter(
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
    pollUpdateCallback = pollCallback,
    pollAnswerUpdateCallback = pollAnswerCallback,
    unknownUpdateTypeCallback = unknownCallback
)