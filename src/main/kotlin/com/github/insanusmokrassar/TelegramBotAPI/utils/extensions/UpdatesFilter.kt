package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.MediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter

@Deprecated(
    "Replaced in separated package",
    ReplaceWith(
        "UpdatesFilter",
        "com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter"
    )
)
typealias UpdatesFilter = UpdatesFilter

@Deprecated(
    "Replaced in separated package",
    ReplaceWith(
        "createSimpleUpdateFilter",
        "com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.createSimpleUpdateFilter"
    )
)
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
    preCheckoutQueryCallback: UpdateReceiver<PreCheckoutQueryUpdate>? = null
): UpdatesFilter = com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.createSimpleUpdateFilter(
    messageCallback,
    mediaGroupCallback,
    editedMessageCallback,
    channelPostCallback,
    editedChannelPostCallback,
    chosenInlineResultCallback,
    inlineQueryCallback,
    callbackQueryCallback,
    shippingQueryCallback,
    preCheckoutQueryCallback
)
