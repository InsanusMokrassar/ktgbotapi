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
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
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
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ContactContent> = sendContact(
    chatId = replyInChatId,
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = lastName,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    contact: Contact,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ContactContent> = sendContact(
    chatId = replyInChatId,
    contact = contact,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Dice

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithDice(
    to: AccessibleMessage,
    animationType: DiceAnimationType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<DiceContent> = sendDice(
    chatId = replyInChatId,
    animationType = animationType,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    animationType: DiceAnimationType,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<DiceContent> = replyWithDice(
    to = to,
    animationType = animationType,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)


// Checklist

public suspend inline fun TelegramBot.replyWithChecklist(
    to: AccessibleMessage,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithChecklist(
    to: BusinessContentMessage<*>,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = to.businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: BusinessContentMessage<*>,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = to.businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    latitude: Double,
    longitude: Double,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<StaticLocationContent> = sendLocation(
    chatId = replyInChatId,
    latitude = latitude,
    longitude = longitude,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    location: StaticLocation,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<StaticLocationContent> = sendLocation(
    chatId = replyInChatId,
    location = location,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Text message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<TextContent> = sendTextMessage(
    chatId = replyInChatId,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<TextContent> = sendTextMessage(
    chatId = replyInChatId,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = reply(
    to = to,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = reply(
    to = to,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)


// Venue

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VenueContent> = sendVenue(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VenueContent> = sendVenue(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    venue: Venue,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VenueContent> = sendVenue(
    chatId = replyInChatId,
    venue = venue,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Game

public suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    gameShortName: String,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<GameContent> = sendGame(
    chatId = replyInChatId,
    gameShortName = gameShortName,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithGame(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<GameContent> = sendGame(
    chatId = replyInChatId,
    gameShortName = game.title,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<GameContent> = replyWithGame(
    to = to,
    game = game,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)


// Animation

public suspend inline fun TelegramBot.replyWithAnimation(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AnimationContent> = sendAnimation(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AnimationContent> = sendAnimation(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAnimation(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AnimationContent> = sendAnimation(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AnimationContent> = sendAnimation(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Audio

public suspend inline fun TelegramBot.replyWithAudio(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    duration = duration,
    performer = performer,
    title = title,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    text = text,
    parseMode = parseMode,
    title = title,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAudio(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    thumb = thumb,
    entities = entities,
    duration = duration,
    performer = performer,
    title = title,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    entities = entities,
    title = title,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Documents

public suspend inline fun TelegramBot.replyWithDocument(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.replyWithDocument(
    to: AccessibleMessage,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    entities = entities,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
public suspend inline fun TelegramBot.replyWithMediaGroup(
    to: AccessibleMessage,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
): ContentMessage<MediaGroupContent<MediaGroupPartContent>> = sendMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithPlaylist(
    to: AccessibleMessage,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
): ContentMessage<MediaGroupContent<AudioContent>> = sendPlaylist(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithDocuments(
    to: AccessibleMessage,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
): ContentMessage<MediaGroupContent<DocumentContent>> = sendDocumentsGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithGallery(
    to: AccessibleMessage,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
): ContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = sendVisualMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)


// Photo

public suspend inline fun TelegramBot.replyWithPhoto(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithPhoto(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Sticker

public suspend inline fun TelegramBot.replyWithSticker(
    to: AccessibleMessage,
    sticker: InputFile,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<StickerContent> = sendSticker(
    chatId = replyInChatId,
    sticker = sticker,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    sticker: Sticker,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<StickerContent> = sendSticker(
    chatId = replyInChatId,
    sticker = sticker,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Videos

public suspend inline fun TelegramBot.replyWithVideo(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoContent> = sendVideo(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoContent> = sendVideo(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithVideo(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoContent> = sendVideo(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoContent> = sendVideo(
    chatId = replyInChatId,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// VideoNotes

public suspend inline fun TelegramBot.replyWithVideoNote(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoNoteContent> = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    thumb = thumb,
    duration = duration,
    size = size,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VideoNoteContent> = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Voice

public suspend inline fun TelegramBot.replyWithVoice(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    text = text,
    parseMode = parseMode,
    duration = duration,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithVoice(
    to: AccessibleMessage,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    entities = entities,
    duration = duration,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    entities = entities,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<InvoiceContent> = sendInvoice(
    chatId = replyInChatId,
    title = title,
    description = description,
    payload = payload,
    providerToken = providerToken,
    currency = currency,
    prices = prices,
    maxTipAmount = maxTipAmount,
    suggestedTipAmounts = suggestedTipAmounts,
    startParameter = startParameter,
    providerData = providerData,
    requireName = requireName,
    requirePhoneNumber = requirePhoneNumber,
    requireEmail = requireEmail,
    requireShippingAddress = requireShippingAddress,
    shouldSendPhoneNumberToProvider = shouldSendPhoneNumberToProvider,
    shouldSendEmailToProvider = shouldSendEmailToProvider,
    priceDependOnShipAddress = priceDependOnShipAddress,
    threadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<InvoiceContent> = sendInvoice(
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
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Polls


public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    isAnonymous = isAnonymous,
    isClosed = allowMultipleAnswers,
    allowMultipleAnswers = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    closeInfo = closeInfo,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowMultipleAnswers = allowMultipleAnswers,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    closeInfo = closeInfo,
    isAnonymous = isAnonymous,
    isClosed = allowMultipleAnswers,
    allowMultipleAnswers = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionId = correctOptionId,
    closeInfo = closeInfo,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = when (poll) {
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
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
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
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        replyMarkup = replyMarkup
    )
}
public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<PollContent> = when (poll) {
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
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
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
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        replyMarkup = replyMarkup
    )
}


public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChatId,
    messageId = messageId,
    toChatId = replyInChatId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = reply(
    to = to,
    fromChatId = fromChat.id,
    messageId = messageId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: AccessibleMessage,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = reply(
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
    allowPaidBroadcast = allowPaidBroadcast,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): AccessibleMessage = execute(
    content.createResend(
        chatId = replyInChatId,
        messageThreadId = replyInThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId),
        replyMarkup = replyMarkup,
    )
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
): Unit = handleLiveLocation(
    chatId = replyInChatId,
    locationsFlow = locationsFlow,
    liveTimeMillis = liveTimeMillis,
    threadId = replyInThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId)
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLocation")
@JsName("replyLiveLocationWithLocation")
public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
) {
    handleLiveLocation(
        chatId = replyInChatId,
        locationsFlow = locationsFlow,
        liveTimeMillis = liveTimeMillis,
        threadId = replyInThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId)
    )
}

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLatLong")
@JsName("replyLiveLocationWithLatLong")
public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
) {
    handleLiveLocation(
        chatId = replyInChatId,
        locationsFlow = locationsFlow,
        liveTimeMillis = liveTimeMillis,
        threadId = replyInThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId)
    )
}

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    mediaFile: TelegramMediaFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    content: TextedMediaContent,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
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
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    entities: TextSourcesList,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = to.chat.id,
        starCount = starCount,
        media = media,
        entities = entities,
        payload = payload,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
        )
    )
}

public suspend fun TelegramBot.reply(
    to: AccessibleMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    text: String? = null,
    parseMode: ParseMode? = null,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = to.chat.id,
        starCount = starCount,
        media = media,
        text = text,
        parseMode = parseMode,
        payload = payload,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId
        )
    )
}