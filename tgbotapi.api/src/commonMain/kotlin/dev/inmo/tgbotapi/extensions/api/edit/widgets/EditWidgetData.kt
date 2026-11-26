package dev.inmo.tgbotapi.extensions.api.edit.widgets

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.widgets.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.widgets.WidgetId

public suspend fun TelegramBot.editMessageWidgetData(
    chatId: ChatIdentifier,
    messageId: MessageId,
    publicData: String? = null,
    chatData: String? = null
): Unit = execute(EditChatMessageWidgetData(chatId, messageId, publicData, chatData))

public suspend fun TelegramBot.editMessageWidgetData(
    chat: Chat,
    messageId: MessageId,
    publicData: String? = null,
    chatData: String? = null
): Unit = editMessageWidgetData(chat.id, messageId, publicData, chatData)

public suspend fun TelegramBot.editMessageWidgetData(
    message: AccessibleMessage,
    publicData: String? = null,
    chatData: String? = null
): Unit = editMessageWidgetData(message.chat.id, message.messageId, publicData, chatData)

public suspend fun TelegramBot.editMessageWidgetData(
    inlineMessageId: InlineMessageId,
    publicData: String? = null,
    chatData: String? = null
): Unit = execute(EditInlineMessageWidgetData(inlineMessageId, publicData, chatData))

public suspend fun TelegramBot.editSharedWidgetData(
    widgetId: WidgetId,
    publicData: String,
    cacheTime: Seconds? = null
): Unit = execute(EditSharedWidgetData(widgetId, publicData, cacheTime))
