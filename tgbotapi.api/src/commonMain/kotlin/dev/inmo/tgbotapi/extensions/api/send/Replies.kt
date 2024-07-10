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
import dev.inmo.tgbotapi.types.message.textsources.TextSource
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
    to: AccessibleMessage,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    contact: Contact,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)


// Dice

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.replyWithDice(
    to: AccessibleMessage,
    animationType: DiceAnimationType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(replyInChatId, animationType, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animationType: DiceAnimationType,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(to, animationType, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    latitude: Double,
    longitude: Double,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    location: StaticLocation,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)


// Text message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Venue

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    venue: Venue,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    gameShortName: String,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, gameShortName, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, game.title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(to, game, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    to: AccessibleMessage,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId = replyInChatId,
    animation = animation,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId = replyInChatId,
    animation = animation,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.replyWithAnimation(
    to: AccessibleMessage,
    animation: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId = replyInChatId,
    animation = animation,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animation: AnimationFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    chatId = replyInChatId,
    animation = animation,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


// Audio

suspend inline fun TelegramBot.replyWithAudio(
    to: AccessibleMessage,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, text, parseMode, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, text, parseMode, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: AccessibleMessage,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, entities, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, entities, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    to: AccessibleMessage,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    to: AccessibleMessage,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    to: AccessibleMessage,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply)
)

suspend inline fun TelegramBot.replyWithPlaylist(
    to: AccessibleMessage,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply)
)

suspend inline fun TelegramBot.replyWithDocuments(
    to: AccessibleMessage,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply)
)

suspend inline fun TelegramBot.replyWithGallery(
    to: AccessibleMessage,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply)
)


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(
    chatId = replyInChatId,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    to: AccessibleMessage,
    sticker: InputFile,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    sticker: Sticker,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    to: AccessibleMessage,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId = replyInChatId,
    video = video,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId = replyInChatId,
    video = video,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.replyWithVideo(
    to: AccessibleMessage,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId = replyInChatId,
    video = video,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(
    chatId = replyInChatId,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true),
    replyMarkup = replyMarkup
)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    to: AccessibleMessage,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    thumb = thumb,
    duration = duration,
    size = size,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true),
    replyMarkup = replyMarkup
)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    to: AccessibleMessage,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    to: AccessibleMessage,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
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
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(replyInChatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, replyInThreadId, disableNotification, protectContent, effectId, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    title: String,
    description: String,
    payload: String,
    price: LabeledPrice,
    startParameter: StartParameter? = null,
    providerData: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(
    chatId = replyInChatId,
    title = title,
    description = description,
    payload = payload,
    price = price,
    startParameter = startParameter,
    providerData = providerData,
    threadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true),
    replyMarkup = replyMarkup
)


// Polls


suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: RegularPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, question, options, closeInfo, questionParseMode, isAnonymous, allowMultipleAnswers, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, questionTextSources, options, closeInfo, isAnonymous, isClosed, allowMultipleAnswers, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: RegularPoll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, questionTextSources, options, closeInfo, isAnonymous, allowMultipleAnswers, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
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
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
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
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
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
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    explanationTextSources: List<TextSource>? = quizPoll.explanationTextSources,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, effectId, ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: Poll,
    question: String,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (poll) {
    is RegularPoll -> reply(
        toChatId = to.chat.id,
        toMessageId = to.messageId,
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
        toChatId = to.chat.id,
        toMessageId = to.messageId,
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
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
}
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: Poll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (poll) {
    is RegularPoll -> reply(
        toChatId = to.chat.id,
        toMessageId = to.messageId,
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
        toChatId = to.chat.id,
        toMessageId = to.messageId,
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
    to: AccessibleMessage,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply == true),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, fromChat.id, messageId, text, parseMode, showCaptionAboveMedia, replyInChatId, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(
    to = to,
    fromChat = copy.chat,
    messageId = copy.messageId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): AccessibleMessage = execute(
    content.createResend(
        replyInChatId,
        replyInThreadId,
        replyInBusinessConnectionId,
        disableNotification,
        protectContent,
        effectId,
        ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true),
        replyMarkup
    )
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
    ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true)
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLocation")
@JsName("replyLiveLocationWithLocation")
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        chatId = replyInChatId,
        locationsFlow = locationsFlow,
        liveTimeMillis = liveTimeMillis,
        threadId = replyInThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true)
    )
}

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLatLong")
@JsName("replyLiveLocationWithLatLong")
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
        ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true)
    )
}

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    mediaFile: TelegramMediaFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
    to: AccessibleMessage,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
            to = to,
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
    to: AccessibleMessage,
    content: TextedMediaContent,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
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
            to = to,
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
            to = to,
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
            to = to,
            photoSize = content.media,
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
        is VideoContent -> reply(
            to = to,
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
            to = to,
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
            to = to,
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
    to: AccessibleMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = to.chat.id,
        starCount = starCount,
        media = media,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    )
}

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = to.chat.id,
        starCount = starCount,
        media = media,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    )
}