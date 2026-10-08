package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditEphemeralMessageCaption
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageCaption(
        chatId = chatId,
        receiverUserId = receiverUserId,
        ephemeralMessageId = ephemeralMessageId,
        caption = caption,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        replyMarkup = replyMarkup
    )
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: EphemeralChatId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    caption = caption,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    caption: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    caption = caption,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageCaption(
        chatId = chatId,
        receiverUserId = receiverUserId,
        ephemeralMessageId = ephemeralMessageId,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        replyMarkup = replyMarkup
    )
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chatId: EphemeralChatId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageCaption(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageCaption(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyMarkup = replyMarkup
)
