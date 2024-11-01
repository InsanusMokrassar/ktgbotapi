package dev.inmo.tgbotapi.extensions.api.edit

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.caption.editMessageCaption
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.edit.media.addMessageMedia
import dev.inmo.tgbotapi.extensions.api.edit.media.editMessageMedia
import dev.inmo.tgbotapi.extensions.api.edit.reply_markup.editMessageReplyMarkup
import dev.inmo.tgbotapi.extensions.api.edit.text.editMessageText
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, text, parseMode, businessConnectionId, showCaptionAboveMedia, replyMarkup)
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    entities: List<TextSource>,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup)
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(chatId, messageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(chat, messageId, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds? = null,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(message, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(
    chatId, messageId, location, businessConnectionId, replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(chat, messageId, location, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<LocationContent>,
    location: LiveLocation,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<LiveLocationContent> = editLiveLocation(message, location, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    media: TelegramFreeMedia,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> = editMessageMedia(chatId, messageId, media, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    media: TelegramFreeMedia,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> = editMessageMedia(chat, messageId, media, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<MediaContent>,
    media: TelegramFreeMedia,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> = editMessageMedia(message, media, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.addMedia(
    message: ContentMessage<TextContent>,
    media: TelegramFreeMedia,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> = addMessageMedia(message, media, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MessageContent> = editMessageReplyMarkup(chatId, messageId, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chat: Chat,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MessageContent> = editMessageReplyMarkup(chat, messageId, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: AccessibleMessage,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MessageContent> = editMessageReplyMarkup(message, businessConnectionId, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(chatId, messageId, text, parseMode, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(chatId, messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(chatId, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: String,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(chatId, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = edit(message.chat.id, message.messageId, text, parseMode, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = edit(message.chat.id, message.messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(message, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.edit(
    message: ContentMessage<TextContent>,
    separator: String,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(message, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(message, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: String,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = edit(message, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)
