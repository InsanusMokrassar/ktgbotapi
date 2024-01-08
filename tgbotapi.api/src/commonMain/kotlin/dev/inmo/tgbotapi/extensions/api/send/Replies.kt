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
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    replyInChat,
    phoneNumber,
    firstName,
    lastName,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    replyInChat,
    contact,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(replyInChat, animationType, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animationType: DiceAnimationType,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(to, animationType, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    latitude: Double,
    longitude: Double,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    replyInChat,
    latitude,
    longitude,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    replyInChat,
    location,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    replyInChat,
    text,
    parseMode,
    linkPreviewOptions,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    replyInChat,
    entities,
    linkPreviewOptions,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = reply(to, buildEntities(separator, builderBody), linkPreviewOptions, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChat,
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChat,
    latitude = location.latitude,
    longitude = location.longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    threadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    venue: Venue,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = replyInChat,
    venue = venue,
    threadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply),
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    gameShortName: String,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChat, gameShortName, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    game: Game,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    replyInChat, game.title, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    game: Game,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(to, game, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChat,
    animation,
    thumb,
    text,
    parseMode,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChat, animation, text, parseMode, spoilered, duration, width, height, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    to: AccessibleMessage,
    animation: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    replyInChat,
    animation,
    thumb,
    entities,
    spoilered,
    duration,
    width,
    height,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(replyInChat, animation, entities, spoilered, duration, width, height, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChat, audio, thumb, text, parseMode, duration, performer, title, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChat, audio, text, parseMode, title, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: AccessibleMessage,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChat, audio, thumb, entities, duration, performer, title, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(replyInChat, audio, entities, title, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    to: AccessibleMessage,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChat, document, thumb, text, parseMode, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChat, document, text, parseMode, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    to: AccessibleMessage,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChat, document, thumb, entities, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(replyInChat, document, entities, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    to: AccessibleMessage,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(replyInChat, media, replyInThreadId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithPlaylist(
    to: AccessibleMessage,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(replyInChat, media, replyInThreadId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithDocuments(
    to: AccessibleMessage,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(replyInChat, media, replyInThreadId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))

suspend inline fun TelegramBot.replyWithGallery(
    to: AccessibleMessage,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(replyInChat, media, replyInThreadId, disableNotification, protectContent, replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply))


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, fileId, text, parseMode, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, photo, text, parseMode, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, photoSize, text, parseMode, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    to: AccessibleMessage,
    fileId: InputFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, fileId, entities, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photo: Photo,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, photo, entities, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(replyInChat, photoSize, entities, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    to: AccessibleMessage,
    sticker: InputFile,
    emoji: String? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChat, sticker, replyInThreadId, emoji, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    sticker: Sticker,
    emoji: String? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(replyInChat, sticker, replyInThreadId, emoji, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)


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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChat, video, thumb, text, parseMode, spoilered, duration, width, height, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChat, video, text, parseMode, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: AccessibleMessage,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChat, video, thumb, entities, spoilered, duration, width, height, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    video: VideoFile,
    entities: TextSourcesList,
    spoilered: Boolean = false,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(replyInChat, video, entities, spoilered, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    to: AccessibleMessage,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChat, videoNote, thumb, duration, size, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    videoNote: VideoNoteFile,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(replyInChat, videoNote, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    to: AccessibleMessage,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChat, voice, text, parseMode, duration, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChat, voice, text, parseMode, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    to: AccessibleMessage,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChat, voice, entities, duration, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(replyInChat, voice, entities, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(replyInChat, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChat, question, options, isAnonymous, isClosed, allowMultipleAnswers, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(replyInChat, poll, isClosed, question, options, isAnonymous, allowMultipleAnswers, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChat, question, options, correctOptionId, isAnonymous, isClosed, explanation, parseMode, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChat, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, explanation, parseMode, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    entities: TextSourcesList,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChat, question, options, correctOptionId, isAnonymous, isClosed, entities, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)

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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(replyInChat, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, entities, closeInfo, replyInThreadId, disableNotification, protectContent, ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true), replyMarkup)


suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    poll: Poll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
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
        replyInChat = replyInChat,
        replyInThreadId = replyInThreadId,
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
        replyInChat = replyInChat,
        replyInThreadId = replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    replyInChat,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, fromChat.id, messageId, text, parseMode, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(to, copy.chat.id, copy.messageId, text, parseMode, replyInChat, replyInThreadId, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: MessageContent,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): AccessibleMessage = execute(
    content.createResend(
        replyInChat,
        replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = handleLiveLocation(
    replyInChat,
    locationsFlow,
    liveTimeMillis,
    replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        replyInChat,
        locationsFlow,
        liveTimeMillis,
        replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        replyInChat,
        locationsFlow,
        liveTimeMillis,
        replyInThreadId,
        disableNotification,
        protectContent,
        ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true)
    )
}

suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    mediaFile: TelegramMediaFile,
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (mediaFile) {
        is AudioFile -> reply(
            to = to,
            audio = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationFile -> reply(
            to = to,
            animation = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VoiceFile -> reply(
            to = to,
            voice = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoFile -> reply(
            to = to,
            video = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoNoteFile -> reply(
            to = to,
            videoNote = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is DocumentFile -> reply(
            to = to,
            document = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is Sticker -> reply(
            to = to,
            sticker = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoSize -> reply(
            to = to,
            photoSize = mediaFile,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            to = to,
            document = mediaFile.asDocumentFile(),
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
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
    replyInChat: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChat.threadId,
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
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            to = to,
            audio = content.media,
            entities = entities,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            to = to,
            photoSize = content.media,
            entities = entities,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            to = to,
            video = content.media,
            entities = entities,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            to = to,
            animation = content.media,
            entities = entities,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            to = to,
            document = content.media.asDocumentFile(),
            entities = entities,
            replyInChat = replyInChat,
            replyInThreadId = replyInThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}
