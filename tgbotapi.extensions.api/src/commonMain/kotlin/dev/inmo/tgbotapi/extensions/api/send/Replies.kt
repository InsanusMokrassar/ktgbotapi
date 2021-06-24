package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.games.sendGame
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.extensions.api.send.payments.sendInvoice
import dev.inmo.tgbotapi.extensions.api.send.polls.sendQuizPoll
import dev.inmo.tgbotapi.extensions.api.send.polls.sendRegularPoll
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.rawSendingMediaGroupsWarning
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.RiskFeature


// Contact

suspend inline fun TelegramBot.reply(
    to: Message,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    to.chat,
    phoneNumber,
    firstName,
    lastName,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    contact: Contact,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    to.chat,
    contact,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)


// Dice

suspend inline fun TelegramBot.replyWithDice(
    to: Message,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(to.chat, animationType, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    animationType: DiceAnimationType,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithDice(to, animationType, disableNotification, allowSendingWithoutReply, replyMarkup)


// Location

suspend inline fun TelegramBot.reply(
    to: Message,
    latitude: Double,
    longitude: Double,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    to.chat,
    latitude,
    longitude,
    disableNotification,
    to.messageId,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    location: StaticLocation,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendLocation(
    to.chat,
    location,
    disableNotification,
    to.messageId,
    replyMarkup
)


// Text message

suspend inline fun TelegramBot.reply(
    to: Message,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    to.chat,
    text,
    parseMode,
    disableWebPagePreview,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    entities: TextSourcesList,
    disableWebPagePreview: Boolean? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(
    to.chat,
    entities,
    disableWebPagePreview,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)


// Venue

suspend inline fun TelegramBot.reply(
    to: Message,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chat = to.chat,
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    disableNotification = disableNotification,
    replyToMessageId = to.messageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chat = to.chat,
    latitude = location.latitude,
    longitude = location.longitude,
    title = title,
    address = address,
    foursquareId = foursquareId,
    foursquareType = foursquareType,
    googlePlaceId = googlePlaceId,
    googlePlaceType = googlePlaceType,
    disableNotification = disableNotification,
    replyToMessageId = to.messageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    venue: Venue,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(
    chat = to.chat,
    venue = venue,
    disableNotification = disableNotification,
    replyToMessageId = to.messageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)


// Game

suspend inline fun TelegramBot.replyWithGame(
    to: Message,
    gameShortName: String,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    to.chat, gameShortName, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.replyWithGame(
    to: Message,
    game: Game,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(
    to.chat, game.title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    game: Game,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = replyWithGame(to, game, disableNotification, allowSendingWithoutReply, replyMarkup)


// Animation

suspend inline fun TelegramBot.replyWithAnimation(
    to: Message,
    animation: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    to.chat,
    animation,
    thumb,
    text,
    parseMode,
    duration,
    width,
    height,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(to.chat, animation, text, parseMode, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAnimation(
    to: Message,
    animation: InputFile,
    entities: TextSourcesList,
    thumb: InputFile? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(
    to.chat,
    animation,
    thumb,
    entities,
    duration,
    width,
    height,
    disableNotification,
    to.messageId,
    allowSendingWithoutReply,
    replyMarkup
)

suspend inline fun TelegramBot.reply(
    to: Message,
    animation: AnimationFile,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(to.chat, animation, entities, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Audio

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, thumb, text, parseMode, duration, performer, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, text, parseMode, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithAudio(
    to: Message,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, thumb, entities, duration, performer, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(to.chat, audio, entities, title, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Documents

suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(to.chat, document, thumb, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: Message,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(to.chat, document, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.replyWithDocument(
    to: Message,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(to.chat, document, thumb, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)

suspend inline fun TelegramBot.reply(
    to: Message,
    document: DocumentFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(to.chat, document, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup, disableContentTypeDetection)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(to.chat, media, disableNotification, to.messageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithPlaylist(
    to: Message,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(to.chat, media, disableNotification, to.messageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithDocuments(
    to: Message,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(to.chat, media, disableNotification, to.messageId, allowSendingWithoutReply)

suspend inline fun TelegramBot.replyWithGallery(
    to: Message,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(to.chat, media, disableNotification, to.messageId, allowSendingWithoutReply)


// Photo

suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, fileId, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, photo, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithPhoto(
    to: Message,
    fileId: InputFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, fileId, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    photo: Photo,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(to.chat, photo, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Sticker

suspend inline fun TelegramBot.replyWithSticker(
    to: Message,
    sticker: InputFile,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(to.chat, sticker, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    sticker: Sticker,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(to.chat, sticker, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Videos

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, thumb, text, parseMode, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.replyWithVideo(
    to: Message,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, thumb, entities, duration, width, height, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    video: VideoFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(to.chat, video, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// VideoNotes

suspend inline fun TelegramBot.replyWithVideoNote(
    to: Message,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(to.chat, videoNote, thumb, duration, size, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    videoNote: VideoNoteFile,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(to.chat, videoNote, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Voice

suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, text, parseMode, duration, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, text, parseMode, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


suspend inline fun TelegramBot.replyWithVoice(
    to: Message,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, entities, duration, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    voice: VoiceFile,
    entities: TextSourcesList,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(to.chat, voice, entities, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Invoice

suspend inline fun TelegramBot.reply(
    to: Message,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(to.chat.id, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)


// Polls

suspend inline fun TelegramBot.reply(
    to: Message,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(to.chat, question, options, isAnonymous, isClosed, allowMultipleAnswers, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(to.chat, poll, isClosed, question, options, isAnonymous, allowMultipleAnswers, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    parseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(to.chat, question, options, correctOptionId, isAnonymous, isClosed, explanation, parseMode, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
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
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(to.chat, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, explanation, parseMode, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    entities: TextSourcesList,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(to.chat, question, options, correctOptionId, isAnonymous, isClosed, entities, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    quizPoll: QuizPoll,
    entities: TextSourcesList,
    isClosed: Boolean = false,
    question: String = quizPoll.question,
    options: List<String> = quizPoll.options.map { it.text },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(to.chat, isClosed, quizPoll, question, options, correctOptionId, isAnonymous, entities, closeInfo, disableNotification, to.messageId, allowSendingWithoutReply, replyMarkup)
