@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.*

/**
 * Explicit helpers replying directly to an incoming ephemeral message identified by [chatId]/[ephemeralMessageId]
 * (as opposed to the `reply(to = someMessage, ...)` smart-branch overloads, which detect an ephemeral
 * [dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage] target automatically). The outgoing message
 * is itself sent as ephemeral, addressed to the same [receiverUserId] as the message being replied to.
 *
 * @see dev.inmo.tgbotapi.types.ephemeralReplyParametersOrNull
 */

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.replyToEphemeral(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<TextContent> = sendMessage(
    chatId = chatId,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithPhoto(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = photo,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithLivePhoto(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    livePhoto: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    chatId = chatId,
    livePhoto = livePhoto,
    photo = photo,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithAudio(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    audio: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
    chatId = chatId,
    audio = audio,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithDocument(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    document: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chatId,
    document = document,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithVideo(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    video: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithAnimation(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    animation: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
    chatId = chatId,
    animation = animation,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithVoice(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    chatId = chatId,
    voice = voice,
    text = text,
    parseMode = parseMode,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithVideoNote(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    videoNote: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoNoteContent> = sendVideoNote(
    chatId = chatId,
    videoNote = videoNote,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithSticker(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    sticker: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = chatId,
    sticker = sticker,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Sends a static location (`live_period` is not supported for ephemeral messages, see [SendLocation.Live])
 */
public suspend fun TelegramBot.replyToEphemeralWithLocation(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    latitude: Double,
    longitude: Double,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StaticLocationContent> = sendStaticLocation(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithVenue(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VenueContent> = sendVenue(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralWithContact(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ContactContent> = sendContact(
    chatId = chatId,
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = lastName,
    receiverUserId = receiverUserId,
    replyParameters = ReplyParameters(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)
