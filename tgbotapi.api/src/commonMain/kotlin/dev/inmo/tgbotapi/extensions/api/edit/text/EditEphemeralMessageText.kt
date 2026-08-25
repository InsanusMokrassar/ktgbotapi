package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageText
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageRichText
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/** Edit an ephemeral message with a rich message. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(EditEphemeralMessageRichText(chatId, receiverUserId, ephemeralMessageId, richMessage, replyMarkup))

/** Edit an ephemeral message with a rich message, obtaining recipient and message identifiers from [chatId]. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chatId: EphemeralChatId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(EditEphemeralMessageRichText(chatId, richMessage, replyMarkup))

/** Edit an ephemeral message with a rich message. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageRichText(chat.id, receiverUserId, ephemeralMessageId, richMessage, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, text, parseMode, linkPreviewOptions, replyMarkup)
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId,
    chatId.receiverUser,
    requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    text,
    parseMode,
    linkPreviewOptions,
    replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
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
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, entities, linkPreviewOptions, replyMarkup)
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId,
    chatId.receiverUser,
    requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    entities,
    linkPreviewOptions,
    replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
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
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chatId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
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
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(chat.id, receiverUserId, ephemeralMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)
