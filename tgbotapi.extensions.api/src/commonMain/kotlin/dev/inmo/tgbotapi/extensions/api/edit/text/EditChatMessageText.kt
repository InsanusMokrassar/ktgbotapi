package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditChatMessageText
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent

suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageText(chatId, messageId, text, parseMode, disableWebPagePreview, replyMarkup)
)

suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chat.id, messageId, text, parseMode, disableWebPagePreview, replyMarkup)

suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(message.chat.id, message.messageId, text, parseMode, disableWebPagePreview, replyMarkup)

suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageText(chatId, messageId, entities, disableWebPagePreview, replyMarkup)
)

suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chat.id, messageId, entities, disableWebPagePreview, replyMarkup)

suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(message.chat.id, message.messageId, entities, disableWebPagePreview, replyMarkup)
