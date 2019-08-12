package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.UpdatesPoller
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.KtorUpdatesPoller
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter
import kotlinx.coroutines.*
import java.util.concurrent.Executors

typealias UpdateReceiver<T> = suspend (T) -> Unit

fun RequestsExecutor.startGettingOfUpdates(
    timeoutMillis: Long = 30 * 1000,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    allowedUpdates: List<String>? = null,
    block: UpdateReceiver<Update>
): UpdatesPoller {
    return KtorUpdatesPoller(
        this,
        timeoutMillis.toInt() / 1000,
        allowedUpdates = allowedUpdates ?: ALL_UPDATES_LIST,
        updatesReceiver = block
    ).also {
        it.start(scope)
    }
}

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
    timeoutMillis: Long = 30 * 1000,
    scope: CoroutineScope = GlobalScope
): UpdatesPoller {
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
        preCheckoutQueryCallback,
        pollCallback
    )
    return startGettingOfUpdates(
        timeoutMillis,
        scope,
        filter.allowedUpdates,
        filter.asUpdateReceiver
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
    timeoutMillis: Long = 30 * 1000,
    scope: CoroutineScope = GlobalScope
): UpdatesPoller = startGettingOfUpdates(
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
    timeoutMillis = timeoutMillis,
    scope = scope
)
