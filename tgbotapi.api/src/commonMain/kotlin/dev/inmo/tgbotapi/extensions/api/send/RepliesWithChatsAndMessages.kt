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
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.*
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    toChatId,
    phoneNumber,
    firstName,
    lastName,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    toChatId,
    contact,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(toChatId, animationType, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animationType: DiceAnimationType,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(toChatId, toMessageId, animationType, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    toChatId,
    latitude,
    longitude,
    threadId,
    disableNotification,
    protectContent,
    allowSendingWithoutReply,
    toMessageId,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    toChatId,
    location,
    threadId,
    disableNotification,
    protectContent,
    allowSendingWithoutReply,
    toMessageId,
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
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    toChatId,
    text,
    parseMode,
    disableWebPagePreview,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
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
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    toChatId,
    entities,
    disableWebPagePreview,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
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
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(toChatId, toMessageId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    separator: String,
    disableWebPagePreview: Boolean? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(toChatId, toMessageId, buildEntities(separator, builderBody), disableWebPagePreview, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = toChatId,
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = toChatId,
    latitude = location.latitude,
    longitude = location.longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    venue: Venue,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = toChatId,
    venue = venue,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    gameShortName: String,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    toChatId, gameShortName, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    toChatId, game.title, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(toChatId, toMessageId, game, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    toChatId,
    animation,
    thumb,
    text,
    parseMode,
    spoilered,
    duration,
    width,
    height,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(toChatId, animation, text, parseMode, spoilered, duration, width, height, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    toChatId,
    animation,
    thumb,
    entities,
    spoilered,
    duration,
    width,
    height,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animation: AnimationFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(toChatId, animation, entities, spoilered, duration, width, height, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, thumb, text, parseMode, duration, performer, title, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, text, parseMode, title, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, thumb, entities, duration, performer, title, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, entities, title, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, thumb, text, parseMode, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, text, parseMode, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, thumb, entities, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, entities, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(toChatId, media, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithPlaylist(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(toChatId, media, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithDocuments(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(toChatId, media, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithGallery(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(toChatId, media, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, fileId, text, parseMode, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photo, text, parseMode, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photoSize, text, parseMode, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, fileId, entities, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: Photo,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photo, entities, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photoSize, entities, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: InputFile,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(toChatId, sticker, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: Sticker,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(toChatId, sticker, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, thumb, text, parseMode, spoilered, duration, width, height, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, text, parseMode, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, thumb, entities, spoilered, duration, width, height, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, entities, spoilered, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(toChatId, videoNote, thumb, duration, size, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: VideoNoteFile,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(toChatId, videoNote, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, text, parseMode, duration, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, text, parseMode, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, entities, duration, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, entities, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(toChatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(toChatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(toChatId, poll, isClosed, question, options, isAnonymous, allowMultipleAnswers, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, question, options, correctOptionId, isAnonymous, isClosed, explanation, parseMode, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, explanation, parseMode, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    entities: TextSourcesList,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, question, options, correctOptionId, isAnonymous, isClosed, entities, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    entities: TextSourcesList,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, entities, closeInfo, threadId, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    poll: Poll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
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
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
    is UnknownPollType -> error("Unable to send poll with unknown type ($poll)")
    is QuizPoll -> reply(
        toChatId = toChatId,
        toMessageId = toMessageId,
        quizPoll = poll,
        entities = poll.textSources,
        isClosed = isClosed,
        question = question,
        options = options,
        isAnonymous = isAnonymous,
        closeInfo = closeInfo,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChatId,
    fromChatId,
    messageId,
    text,
    parseMode,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, fromChat.id, messageId, text, parseMode, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    copy: Message,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, copy.chat.id, copy.messageId, text, parseMode, threadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: MessageContent,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    execute(
        content.createResend(
            toChatId,
            threadId,
            disableNotification,
            protectContent,
            toMessageId,
            allowSendingWithoutReply,
            replyMarkup
        )
    )
}

/**
 * Will use [handleLiveLocation] with replying to [message] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = handleLiveLocation(
    toChatId,
    locationsFlow,
    liveTimeMillis,
    threadId,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply
)

/**
 * Will use [handleLiveLocation] with replying to [message] each time new message will be sent by live location update
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        toChatId,
        locationsFlow,
        liveTimeMillis,
        threadId,
        disableNotification,
        protectContent,
        toMessageId,
        allowSendingWithoutReply
    )
}

/**
 * Will use [handleLiveLocation] with replying to [message] each time new message will be sent by live location update
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        toChatId,
        locationsFlow,
        liveTimeMillis,
        threadId,
        disableNotification,
        protectContent,
        toMessageId,
        allowSendingWithoutReply
    )
}

suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    mediaFile: TelegramMediaFile,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (mediaFile) {
        is AudioFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VoiceFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoNoteFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            videoNote = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is DocumentFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is Sticker -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            sticker = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoSize -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = mediaFile,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile.asDocumentFile(),
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
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
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            text = text,
            parseMode = parseMode,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            text = text,
            parseMode = parseMode,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = content.media,
            text = text,
            parseMode = parseMode,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            text = text,
            parseMode = parseMode,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            text = text,
            parseMode = parseMode,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
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
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (content) {
        is VoiceContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = content.media,
            entities = entities,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            entities = entities,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            entities = entities,
            threadId = threadId,
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
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            entities = entities,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            entities = entities,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}
