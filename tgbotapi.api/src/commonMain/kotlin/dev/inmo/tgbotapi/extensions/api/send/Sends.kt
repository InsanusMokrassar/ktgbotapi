package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.games.sendGame
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.extensions.api.send.payments.sendInvoice
import dev.inmo.tgbotapi.extensions.api.send.polls.sendQuizPoll
import dev.inmo.tgbotapi.extensions.api.send.polls.sendRegularPoll
import dev.inmo.tgbotapi.requests.send.media.rawSendingMediaGroupsWarning
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.BotAction
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.*
import kotlin.jvm.JvmName

/**
 * Will execute [sendBotAction] request
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    action: BotAction
) = sendBotAction(chatId, action)

/**
 * Will execute [sendBotAction] request
 */
suspend fun TelegramBot.send(
    chat: Chat,
    action: BotAction
) = sendBotAction(chat, action)

/**
 * Will execute [sendAnimation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chatId, animation, text, parseMode, showCaptionAboveMedia, spoilered, duration, width, height, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAnimation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    animation: AnimationFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chat, animation, text, parseMode, showCaptionAboveMedia, spoilered, duration, width, height, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAnimation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    animation: AnimationFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chatId, animation, entities, showCaptionAboveMedia, spoilered, duration, width, height, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAnimation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    animation: AnimationFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAnimation(chat, animation, entities, showCaptionAboveMedia, spoilered, duration, width, height, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAudio] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chatId, audio, text, parseMode, title, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAudio] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = audio.title,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat, audio, text, parseMode, title, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAudio] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = audio.title,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chatId, audio, entities, title, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendAudio] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = audio.title,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendAudio(chat, audio, entities, title, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendContact] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(chatId, phoneNumber, firstName, lastName, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendContact] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    contact: Contact,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(chatId, contact, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendContact] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(chat, phoneNumber, firstName, lastName, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendContact] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    contact: Contact,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(chat, contact, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendDice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    animationType: DiceAnimationType,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(chatId, animationType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendDice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    animationType: DiceAnimationType,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(chat, animationType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendDocument] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chatId, document, text, parseMode, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * Will execute [sendDocument] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat, document, text, parseMode, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * Will execute [sendDocument] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chatId, document, entities, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * Will execute [sendDocument] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
) = sendDocument(chat, document, entities, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup, disableContentTypeDetection)

/**
 * Will execute [sendGame] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    game: Game,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(chatId, game, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendGame] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    game: Game,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendGame(chat, game, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendInvoice] request
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    chatId: IdChatIdentifier,
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
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(chatId, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, threadId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendInvoice] request
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    user: CommonUser,
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
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(user, title, description, payload, providerToken, currency, prices, maxTipAmount, suggestedTipAmounts, startParameter, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendStaticLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendStaticLocation(chatId, latitude, longitude, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendStaticLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    location: StaticLocation,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendStaticLocation(chatId, location, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendStaticLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendStaticLocation(chat, latitude, longitude, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendStaticLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    location: StaticLocation,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendStaticLocation(chat, location, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendTextMessage] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chatId, text, parseMode, linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendTextMessage] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat, text, parseMode, linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendTextMessage] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chatId, entities, linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = send(chatId, buildEntities(separator, builderBody), linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = send(chatId, buildEntities(separator, builderBody), linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)


/**
 * Will execute [sendTextMessage] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendTextMessage(chat, entities, linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    chat: Chat,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = send(chat, buildEntities(separator, builderBody), linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.send(
    chat: Chat,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = send(chat, buildEntities(separator, builderBody), linkPreviewOptions, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photo, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    photo: Photo,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat, photo, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photoSize, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat, photoSize, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    photo: Photo,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photo, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    photo: Photo,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat, photo, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chatId, photoSize, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendPhoto] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendPhoto(chat, photoSize, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chatId, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chatId, questionTextSources, options, closeInfo, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    poll: RegularPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    isClosed: Boolean = false,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chatId, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    poll: RegularPoll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    isClosed: Boolean = false,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chatId, questionTextSources, options, closeInfo, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chat.id, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    poll: RegularPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    isClosed: Boolean = false,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chat.id, question, options, closeInfo, questionParseMode, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendRegularPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    poll: RegularPoll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    isClosed: Boolean = false,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(chat.id, questionTextSources, options, closeInfo, isAnonymous, isClosed, allowMultipleAnswers, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chatId, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, question, options, correctOptionId, closeInfo, questionParseMode, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chatId, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    explanation: String? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, questionTextSources, options, correctOptionId, closeInfo, explanation, explanationParseMode, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>? = null,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    question: String,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>? = null,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String,
    explanationTextSources: List<TextSource>? = null,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chatId, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, question, options, correctOptionId, closeInfo, questionParseMode, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionId: Int,
    explanationTextSources: List<TextSource>? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    explanationTextSources: List<TextSource>? = quizPoll.explanationTextSources.takeIf { it.isNotEmpty() },
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chatId, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendQuizPoll] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    isClosed: Boolean = false,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    explanationTextSources: List<TextSource>? = quizPoll.explanationTextSources.takeIf { it.isNotEmpty() },
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionId: Int = quizPoll.correctOptionId ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    closeInfo: ScheduledCloseInfo? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(chat.id, questionTextSources, options, correctOptionId, closeInfo, explanationTextSources, isAnonymous, isClosed, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendSticker] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    sticker: Sticker,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chatId, sticker, threadId, businessConnectionId, emoji, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendSticker] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    sticker: Sticker,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendSticker(chat, sticker, threadId, businessConnectionId, emoji, disableNotification, protectContent, effectId, replyParameters, replyMarkup)


/**
 * Will execute [sendLiveLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLiveLocation(
    chatId = chatId,
    latitude = latitude,
    longitude = longitude,
    livePeriod = livePeriod,
    horizontalAccuracy = horizontalAccuracy,
    heading = heading,
    proximityAlertRadius = proximityAlertRadius,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * Will execute [sendLiveLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    location: Location,
    livePeriod: Seconds,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLiveLocation(
    chatId, location, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendLiveLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    livePeriod: Seconds,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLiveLocation(
    chat, latitude, longitude, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendLiveLocation] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    location: Location,
    livePeriod: Seconds,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendLiveLocation(
    chat, location, livePeriod, horizontalAccuracy, heading, proximityAlertRadius, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup
)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chatId, latitude, longitude, title, address, foursquareId, foursquareType, googlePlaceId, googlePlaceType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chat, latitude, longitude, title, address, foursquareId, foursquareType, googlePlaceId, googlePlaceType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chatId, location, title, address, foursquareId, foursquareType, googlePlaceId, googlePlaceType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chat, location, title, address, foursquareId, foursquareType, googlePlaceId, googlePlaceType, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    venue: Venue,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chatId, venue, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVenue] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    venue: Venue,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVenue(chat, venue, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideo] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chatId, video, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideo] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat, video, text, parseMode, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideo] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chatId, video, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideo] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideo(chat, video, entities, showCaptionAboveMedia, spoilered, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideoNote] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    videoNote: VideoNoteFile,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chatId, videoNote, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVideoNote] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    videoNote: VideoNoteFile,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVideoNote(chat, videoNote, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVoice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chatId, voice, text, parseMode, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVoice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.send(
    chat: Chat,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat, voice, text, parseMode, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVoice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chatId: ChatIdentifier,
    voice: VoiceFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chatId, voice, entities, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * Will execute [sendVoice] request
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.send(
    chat: Chat,
    voice: VoiceFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(chat, voice, entities, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters, replyMarkup)

/**
 * @see sendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroup")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroup")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendPlaylist
 */
@JvmName("sendPlaylist")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendPlaylist
 */
@JvmName("sendPlaylist")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendDocumentsGroup
 */
@JvmName("sendDocuments")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendDocumentsGroup
 */
@JvmName("sendDocuments")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroup")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroup")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.send(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)

/**
 * @see sendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.send(
    chat: Chat,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(chat, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters)
