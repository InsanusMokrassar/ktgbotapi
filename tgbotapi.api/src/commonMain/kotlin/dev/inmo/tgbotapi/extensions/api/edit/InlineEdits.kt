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
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(messageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(
    messageId, location, replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(messageId, media, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageReplyMarkup(messageId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(messageId, text, parseMode, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(messageId, entities, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.edit(
    messageId: InlineMessageIdentifier,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = edit(messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)
