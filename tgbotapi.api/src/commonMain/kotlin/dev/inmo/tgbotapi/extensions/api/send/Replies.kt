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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    replyInChatId,
    contact,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(replyInChatId, animationType, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(to, animationType, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    replyInChatId,
    location,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChatId,
    venue = venue,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, gameShortName, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChatId, game.title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(to, game, replyInChatId, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    to: AccessibleMessage,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChatId,
    animation,
    thumb,
    text,
    parseMode,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChatId, animation, text, parseMode, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    to: AccessibleMessage,
    animation: InputFile,
    entities: TextSourcesList,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChatId,
    animation,
    thumb,
    entities,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
    ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animation: AnimationFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChatId, animation, entities, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, text, parseMode, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, text, parseMode, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, thumb, entities, duration, performer, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChatId, audio, entities, title, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, thumb, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChatId, document, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)


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
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithPlaylist(
    to: AccessibleMessage,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithDocuments(
    to: AccessibleMessage,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithGallery(
    to: AccessibleMessage,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(replyInChatId, media, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, fileId, text, parseMode, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photo, text, parseMode, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photoSize, text, parseMode, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, fileId, entities, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: Photo,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photo, entities, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChatId, photoSize, entities, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    sticker: Sticker,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChatId, sticker, replyInThreadId, replyInBusinessConnectionId, emoji, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    to: AccessibleMessage,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, thumb, text, parseMode, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, text, parseMode, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: AccessibleMessage,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, thumb, entities, spoilered, duration, width, height, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChatId, video, entities, spoilered, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChatId, videoNote, thumb, duration, size, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChatId, videoNote, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, text, parseMode, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, duration, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChatId, voice, entities, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(replyInChatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChatId, poll, isClosed, question, options, isAnonymous, allowMultipleAnswers, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, isAnonymous, isClosed, explanation, parseMode, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    quizPoll: QuizPoll,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, explanation, parseMode, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    entities: TextSourcesList,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, question, options, correctOptionId, isAnonymous, isClosed, entities, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    quizPoll: QuizPoll,
    entities: TextSourcesList,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, entities, closeInfo, replyInThreadId, replyInBusinessConnectionId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: Poll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (poll) {
    is RegularPoll -> reply(
        to = to,
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
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
    is UnknownPollType -> error("Unable to send poll with unknown type ($poll)")
    is QuizPoll -> reply(
        to = to,
        quizPoll = poll,
        entities = poll.textSources,
        isClosed = isClosed,
        question = question,
        options = options,
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
    to: AccessibleMessage,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
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
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, fromChat.id, messageId, text, parseMode, replyInChatId, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, copy.chat.id, copy.messageId, text, parseMode, replyInChatId, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): AccessibleMessage = execute(
    content.createResend(
        replyInChatId,
        replyInThreadId,
        replyInBusinessConnectionId,
        disableNotification,
        protectContent,
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
    allowSendingWithoutReply: Boolean? = null
) = handleLiveLocation(
    replyInChatId,
    locationsFlow,
    liveTimeMillis,
    replyInThreadId,
    replyInBusinessConnectionId,
    disableNotification,
    protectContent,
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
        ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true)
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
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}
