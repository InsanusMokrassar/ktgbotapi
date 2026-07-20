package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageText
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, text, parseMode, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(chat.id, receiverUserId, ephemeralMessageId, text, parseMode, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, entities, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(chat.id, receiverUserId, ephemeralMessageId, entities, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" },
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chat.id, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" },
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chat.id, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)
