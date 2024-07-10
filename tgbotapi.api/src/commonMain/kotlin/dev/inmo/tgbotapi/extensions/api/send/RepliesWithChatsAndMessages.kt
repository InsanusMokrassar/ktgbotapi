package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.*
import dev.inmo.tgbotapi.extensions.api.send.games.sendGame
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.extensions.api.send.payments.sendInvoice
import dev.inmo.tgbotapi.extensions.api.send.polls.sendQuizPoll
import dev.inmo.tgbotapi.extensions.api.send.polls.sendRegularPoll
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.rawSendingMediaGroupsWarning
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import dev.inmo.tgbotapi.types.message.payments.toTelegramMediaPhoto
import dev.inmo.tgbotapi.types.message.payments.toTelegramPaidMediaVideo
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.*
import kotlinx.coroutines.flow.Flow
import kotlin.js.JsName
import kotlin.jvm.JvmName


// Contact

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    replyInChatId,
    phoneNumber,
    firstName,
    lastName,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    contact: Contact,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    replyInChatId,
    contact,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)


// Dice

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.replyWithDice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animationType: DiceAnimationType? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(replyInChatId, animationType, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animationType: DiceAnimationType,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(toChatId, toMessageId, animationType, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    latitude: Double,
    longitude: Double,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    replyInChatId,
    latitude,
    longitude,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    location: StaticLocation,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    replyInChatId,
    location,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)


// Text message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    replyInChatId,
    text,
    parseMode,
    linkPreviewOptions,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    replyInChatId,
    entities,
    linkPreviewOptions,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(toChatId, toMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(toChatId, toMessageId, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Venue

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChatId,
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChatId,
    latitude = location.latitude,
    longitude = location.longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    venue: Venue,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChatId,
    venue = venue,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    gameShortName: String,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, gameShortName, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, game.title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(toChatId, toMessageId, game, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChatId,
    animation,
    thumb,
    text,
    parseMode,
    showCaptionAboveMedia,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChatId, animation, text, parseMode, showCaptionAboveMedia, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChatId,
    animation,
    thumb,
    entities,
    showCaptionAboveMedia,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: AnimationFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChatId, animation, entities, showCaptionAboveMedia, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Audio

suspend inline fun TelegramBot.replyWithAudio(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, text, parseMode, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, text, parseMode, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, entities, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, entities, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithPlaylist(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithDocuments(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithGallery(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply))


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, fileId, text, parseMode, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photo, text, parseMode, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photoSize, text, parseMode, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, fileId, entities, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photo, entities, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photoSize, entities, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: InputFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: Sticker,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, thumb, text, parseMode, showCaptionAboveMedia, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, text, parseMode, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, thumb, entities, showCaptionAboveMedia, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, entities, showCaptionAboveMedia, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChatId, videoNote, thumb, duration, size, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChatId, videoNote, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: Currency,
    prices: List<LabeledPrice>,
    maxTipAmount: Int? = null,
    suggestedTipAmounts: List<Int>? = null,
    startParameter: StartParameter? = null,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(replyInChatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, replyInThreadId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: RegularPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, question, options, closeInfo, questionParseMode, isAnonymous, allowMultipleAnswers, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, questionTextSources, options, closeInfo, isAnonymous, isClosed, allowMultipleAnswers, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: RegularPoll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, questionTextSources, options, closeInfo, isAnonymous, allowMultipleAnswers, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    question: String,
    explanation: String?,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    explanation: String?,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    question: String,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    explanationTextSources: List<TextSource>? = quizPoll.explanationTextSources,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: Poll,
    question: String,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (poll) {
    is RegularPoll -> reply(
        toChatId = toChatId,
        toMessageId = toMessageId,
        poll = poll,
        isClosed = isClosed,
        question = question,
        options = options,
        isAnonymous = isAnonymous,
        allowMultipleAnswers = isAnonymous,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
    is UnknownPollType -> error("Unable to send poll with unknown type ($poll)")
    is QuizPoll -> reply(
        toChatId = toChatId,
        toMessageId = toMessageId,
        quizPoll = poll,
        explanationTextSources = poll.explanationTextSources,
        question = question,
        options = options,
        isClosed = isClosed,
        isAnonymous = isAnonymous,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
}
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: Poll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (poll) {
    is RegularPoll -> reply(
        toChatId = toChatId,
        toMessageId = toMessageId,
        poll = poll,
        questionTextSources = questionTextSources,
        options = options,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowMultipleAnswers = isAnonymous,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
    is UnknownPollType -> error("Unable to send poll with unknown type ($poll)")
    is QuizPoll -> reply(
        toChatId = toChatId,
        toMessageId = toMessageId,
        quizPoll = poll,
        questionTextSources = questionTextSources,
        explanationTextSources = poll.explanationTextSources,
        options = options,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
}


suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    replyInChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    showCaptionAboveMedia,
    replyInThreadId,
    disableNotification,
    protectContent,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, fromChat.id, messageId, text, parseMode, showCaptionAboveMedia, replyInChatId, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, copy.chat.id, copy.messageId, text, parseMode, showCaptionAboveMedia, replyInChatId, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    execute(
        content.createResend(
            chatId = replyInChatId,
            messageThreadId = replyInThreadId,
            businessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply),
            replyMarkup = replyMarkup
        )
    )
}

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = handleLiveLocation(
    replyInChatId,
    locationsFlow,
    liveTimeMillis,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    effectId,
    ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply)
)

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLocationChatIdAndMessageId")
@JsName("replyLiveLocationWithLocationChatIdAndMessageId")
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        replyInChatId,
        locationsFlow,
        liveTimeMillis,
        replyInThreadId,
        replyInBusinessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply)
    )
}

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLatLongChatIdAndMessageId")
@JsName("replyLiveLocationWithLatLongChatIdAndMessageId")
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        replyInChatId,
        locationsFlow,
        liveTimeMillis,
        replyInThreadId,
        replyInBusinessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply)
    )
}

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    mediaFile: TelegramMediaFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (mediaFile) {
        is AudioFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VoiceFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoNoteFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            videoNote = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is DocumentFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is Sticker -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            sticker = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoSize -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile.asDocumentFile(),
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (content) {
        is VoiceContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: TextedMediaContent,
    entities: List<TextSource>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (content) {
        is VoiceContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: PaidMediaInfoContent,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    parseMode: ParseMode? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    val media = content.paidMediaInfo.media.mapNotNull {
        when (it) {
            is PaidMedia.Video -> it.toTelegramPaidMediaVideo()
            is PaidMedia.Photo -> it.toTelegramMediaPhoto()
            is PaidMedia.Preview, is PaidMedia.Unknown -> null
        }
    }
    sendPaidMedia(
        chatId = replyInChatId,
        starCount = content.paidMediaInfo.stars,
        media = media,
        text = content.text,
        parseMode = parseMode,
        showCaptionAboveMedia = content.showCaptionAboveMedia,
        threadId = replyInThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = toMessageId,
            chatIdentifier = toChatId,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    )
}

