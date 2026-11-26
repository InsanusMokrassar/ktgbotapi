package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendWidget
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputWidgetMessageContent
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

/** Sends the supplied widget content. */
public suspend fun TelegramBot.sendWidget(
    chatId: ChatIdentifier,
    widget: InputWidgetMessageContent,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendWidget(
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
public suspend fun TelegramBot.sendWidget(
    chat: Chat,
    widget: InputWidgetMessageContent,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendWidget(
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
public suspend fun TelegramBot.sendWidget(
    chatId: ChatIdentifier,
    html: String,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendWidget(
        chatId = chatId,
        html = html,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared,
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
public suspend fun TelegramBot.sendWidget(
    chat: Chat,
    html: String,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendWidget(
    chatId = chat.id,
    html = html,
    width = width,
    height = height,
    publicData = publicData,
    chatData = chatData,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    allowQueries = allowQueries,
    isShared = isShared,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a widget with a caption assembled from text entities. */
public suspend fun TelegramBot.sendWidget(
    chatId: ChatIdentifier,
    html: String,
    entities: TextSourcesList,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = execute(
    SendWidget(
        chatId = chatId,
        html = html,
        entities = entities,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared,
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
public suspend fun TelegramBot.sendWidget(
    chat: Chat,
    html: String,
    entities: TextSourcesList,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<WidgetContent> = sendWidget(
    chatId = chat.id,
    html = html,
    entities = entities,
    width = width,
    height = height,
    publicData = publicData,
    chatData = chatData,
    showCaptionAboveMedia = showCaptionAboveMedia,
    allowQueries = allowQueries,
    isShared = isShared,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
