package dev.inmo.tgbotapi.extensions.api.edit

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.caption.editMessageCaption
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.edit.media.editMessageMedia
import dev.inmo.tgbotapi.extensions.api.edit.reply_markup.editMessageReplyMarkup
import dev.inmo.tgbotapi.extensions.api.edit.text.editMessageText
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, text, parseMode, replyMarkup)
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, entities, replyMarkup)
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chatId, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat, messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(
    chatId, messageId, location, replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(chat, messageId, location, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(message, location, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chatId, messageId, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chat, messageId, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<MediaContent>,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(message, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(chatId, messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(chat, messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: Message,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(message, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chatId, messageId, text, parseMode, disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chatId, messageId, entities, disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(chatId, messageId, buildEntities(separator, builderBody), disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(chatId, messageId, buildEntities(separator, builderBody), disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = edit(message.chat.id, message.messageId, text, parseMode, disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = edit(message.chat.id, message.messageId, entities, disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: TextSource? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(message, buildEntities(separator, builderBody), disableWebPagePreview, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(message, buildEntities(separator, builderBody), disableWebPagePreview, replyMarkup)
