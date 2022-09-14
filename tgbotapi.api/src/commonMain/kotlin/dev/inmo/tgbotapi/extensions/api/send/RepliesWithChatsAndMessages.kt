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
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.flow.Flow
import kotlin.js.JsName
import kotlin.jvm.JvmName


// Contact

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    toChatId,
    phoneNumber,
    firstName,
    lastName,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    contact: Contact,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    toChatId,
    contact,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(toChatId, animationType, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    animationType: DiceAnimationType,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(toChatId, toMessageId, animationType, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    toChatId,
    latitude,
    longitude,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    location: StaticLocation,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    toChatId,
    location,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    toChatId,
    text,
    parseMode,
    disableWebPagePreview,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    toChatId,
    entities,
    disableWebPagePreview,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)


// Venue

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
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
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
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
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    venue: Venue,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chatId = toChatId,
    venue = venue,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = toMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    toChatId: ChatId,
    toMessageId: MessageId,
    gameShortName: String,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    toChatId, gameShortName, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    toChatId: ChatId,
    toMessageId: MessageId,
    game: Game,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    toChatId, game.title, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    game: Game,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(toChatId, toMessageId, game, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: ChatId,
    toMessageId: MessageId,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
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
    duration,
    width,
    height,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(toChatId, animation, text, parseMode, duration, width, height, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    toChatId: ChatId,
    toMessageId: MessageId,
    animation: InputFile,
    entities: TextSourcesList,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    toChatId,
    animation,
    thumb,
    entities,
    duration,
    width,
    height,
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    animation: AnimationFile,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(toChatId, animation, entities, duration, width, height, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Audio

suspend inline fun TelegramBot.replyWithAudio(
    toChatId: ChatId,
    toMessageId: MessageId,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, thumb, text, parseMode, duration, performer, title, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, text, parseMode, title, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    toChatId: ChatId,
    toMessageId: MessageId,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, thumb, entities, duration, performer, title, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(toChatId, audio, entities, title, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: ChatId,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, thumb, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    toChatId: ChatId,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, thumb, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(toChatId, document, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    toChatId: ChatId,
    toMessageId: MessageId,
    media: List<MediaGroupMemberTelegramMedia>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(toChatId, media, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithPlaylist(
    toChatId: ChatId,
    toMessageId: MessageId,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(toChatId, media, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithDocuments(
    toChatId: ChatId,
    toMessageId: MessageId,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(toChatId, media, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithGallery(
    toChatId: ChatId,
    toMessageId: MessageId,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(toChatId, media, disableNotification, protectContent, toMessageId, allowSendingWithoutReply)


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: ChatId,
    toMessageId: MessageId,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, fileId, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photo, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photoSize, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: ChatId,
    toMessageId: MessageId,
    fileId: InputFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, fileId, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    photo: Photo,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photo, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(toChatId, photoSize, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    toChatId: ChatId,
    toMessageId: MessageId,
    sticker: InputFile,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(toChatId, sticker, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    sticker: Sticker,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(toChatId, sticker, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: ChatId,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, thumb, text, parseMode, duration, width, height, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    toChatId: ChatId,
    toMessageId: MessageId,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, thumb, entities, duration, width, height, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    video: VideoFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(toChatId, video, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    toChatId: ChatId,
    toMessageId: MessageId,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(toChatId, videoNote, thumb, duration, size, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    videoNote: VideoNoteFile,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(toChatId, videoNote, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    toChatId: ChatId,
    toMessageId: MessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, text, parseMode, duration, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, text, parseMode, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    toChatId: ChatId,
    toMessageId: MessageId,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, entities, duration, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(toChatId, voice, entities, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
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
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(toChatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(toChatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(toChatId, poll, isClosed, question, options, isAnonymous, allowMultipleAnswers, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, question, options, correctOptionId, isAnonymous, isClosed, explanation, parseMode, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
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
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, explanation, parseMode, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    entities: TextSourcesList,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, question, options, correctOptionId, isAnonymous, isClosed, entities, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    quizPoll: QuizPoll,
    entities: TextSourcesList,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(toChatId, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, entities, closeInfo, disableNotification, protectContent, toMessageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    poll: Poll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
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
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
}


suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
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
    disableNotification,
    protectContent,
    toMessageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, fromChat.id, messageId, text, parseMode, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    copy: Message,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = reply(toChatId, toMessageId, copy.chat.id, copy.messageId, text, parseMode, disableNotification, protectContent, allowSendingWithoutReply, replyMarkup)

suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    content: MessageContent,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    execute(
        content.createResend(
            toChatId,
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
    toChatId: ChatId,
    toMessageId: MessageId,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = handleLiveLocation(
    toChatId,
    locationsFlow,
    liveTimeMillis,
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
@JvmName("replyLiveLocationWithLocation")
@JsName("replyLiveLocationWithLocation")
suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        toChatId,
        locationsFlow,
        liveTimeMillis,
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
@JvmName("replyLiveLocationWithLatLong")
@JsName("replyLiveLocationWithLatLong")
suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        toChatId,
        locationsFlow,
        liveTimeMillis,
        disableNotification,
        protectContent,
        toMessageId,
        allowSendingWithoutReply
    )
}

suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    mediaFile: TelegramMediaFile,
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AnimationFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VoiceFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is VideoNoteFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            videoNote = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is DocumentFile -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is Sticker -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            sticker = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is PhotoSize -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = mediaFile,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        else -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile.asDocumentFile(),
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}

suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            text = text,
            parseMode = parseMode,
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}

suspend fun TelegramBot.reply(
    toChatId: ChatId,
    toMessageId: MessageId,
    content: TextedMediaContent,
    entities: List<TextSource>,
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            entities = entities,
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }
}
