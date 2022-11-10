package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(
        chatId,
        text,
        parseMode,
        disableWebPagePreview,
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, text, parseMode, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, text, parseMode, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, text, parseMode, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendTextMessage(chatId, entities, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendMessage(chatId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendMessage(chatId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(
    chatId, entities, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendTextMessage(chatId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendTextMessage(chatId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendMessage(
    chat: Chat,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendMessage(chat.id, entities, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendMessage(
    chat: Chat,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendMessage(chat, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendMessage(
    chat: Chat,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendMessage(chat, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat.id, entities, disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendTextMessage(chat, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = sendTextMessage(chat, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply, replyMarkup)
