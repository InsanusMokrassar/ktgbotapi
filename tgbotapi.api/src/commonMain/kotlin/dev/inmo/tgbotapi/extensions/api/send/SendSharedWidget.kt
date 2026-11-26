package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendSharedWidget
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputSharedWidgetMessageContent
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.widgets.WidgetId

/** Sends the supplied widget content. */
public suspend fun TelegramBot.sendSharedWidget(
    chatId: ChatIdentifier,
    widget: InputSharedWidgetMessageContent,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendSharedWidget(
        chatId = chatId,
        widget = widget,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/** Sends the supplied widget content. */
public suspend fun TelegramBot.sendSharedWidget(
    chat: Chat,
    widget: InputSharedWidgetMessageContent,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendSharedWidget(
    chatId = chat.id,
    widget = widget,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a widget with an optional caption and parse mode. */
public suspend fun TelegramBot.sendSharedWidget(
    chatId: ChatIdentifier,
    widgetId: WidgetId,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendSharedWidget(
        chatId = chatId,
        widgetId = widgetId,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/** Sends a widget with an optional caption and parse mode. */
public suspend fun TelegramBot.sendSharedWidget(
    chat: Chat,
    widgetId: WidgetId,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendSharedWidget(
    chatId = chat.id,
    widgetId = widgetId,
    chatData = chatData,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a widget with a caption assembled from text entities. */
public suspend fun TelegramBot.sendSharedWidget(
    chatId: ChatIdentifier,
    widgetId: WidgetId,
    entities: TextSourcesList,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendSharedWidget(
        chatId = chatId,
        widgetId = widgetId,
        entities = entities,
        chatData = chatData,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/** Sends a widget with a caption assembled from text entities. */
public suspend fun TelegramBot.sendSharedWidget(
    chat: Chat,
    widgetId: WidgetId,
    entities: TextSourcesList,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendSharedWidget(
    chatId = chat.id,
    widgetId = widgetId,
    entities = entities,
    chatData = chatData,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
