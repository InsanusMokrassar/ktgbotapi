package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.StopChatMessageLiveLocation
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.types.message.content.StaticLocationContent

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopLiveLocation(
    chatId: ChatIdentifier,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<StaticLocationContent> = execute(
    StopChatMessageLiveLocation(
        chatId, messageId, businessConnectionId, replyMarkup
    )
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopLiveLocation(
    chat: Chat,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<StaticLocationContent> = stopLiveLocation(chat.id, messageId, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.stopLiveLocation(
    message: ContentMessage<LocationContent>,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<StaticLocationContent> = stopLiveLocation(message.chat, message.messageId, businessConnectionId, replyMarkup)
