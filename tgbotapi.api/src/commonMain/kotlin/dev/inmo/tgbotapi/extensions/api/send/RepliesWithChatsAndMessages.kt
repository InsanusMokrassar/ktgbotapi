@file:Suppress("KDocUnresolvedReference")

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
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.games.Game
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
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
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    contact: Contact,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Dice

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithDice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animationType: DiceAnimationType? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    animationType: DiceAnimationType,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<DiceContent> = replyWithDice(
    toChatId = toChatId,
    toMessageId = toMessageId,
    animationType = animationType,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithChecklist(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = toChatId,
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
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = toChatId,
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
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    latitude: Double,
    longitude: Double,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    location: StaticLocation,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Text message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    toChatId = toChatId,
    toMessageId = toMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    toChatId = toChatId,
    toMessageId = toMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    venue: Venue,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Game

public suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    gameShortName: String,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithGame(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    game: Game,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): ContentMessage<GameContent> = replyWithGame(
    toChatId = toChatId,
    toMessageId = toMessageId,
    game = game,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAnimation(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Audio

public suspend inline fun TelegramBot.replyWithAudio(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAudio(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Documents

public suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.replyWithDocument(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
public suspend inline fun TelegramBot.replyWithMediaGroup(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithPlaylist(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithDocuments(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

public suspend inline fun TelegramBot.replyWithGallery(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)


// Photo

public suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithPhoto(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Sticker

public suspend inline fun TelegramBot.replyWithSticker(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: InputFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    emoji: String? = null,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    sticker: Sticker,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    emoji: String? = null,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Videos

public suspend inline fun TelegramBot.replyWithVideo(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithVideo(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// VideoNotes

public suspend inline fun TelegramBot.replyWithVideoNote(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Voice

public suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithVoice(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


// Polls

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        replyMarkup = replyMarkup
    )
}
public suspend inline fun TelegramBot.reply(
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = reply(
    toChatId = toChatId,
    toMessageId = toMessageId,
    fromChatId = fromChat.id,
    messageId = messageId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    copy: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = reply(
    toChatId = toChatId,
    toMessageId = toMessageId,
    fromChatId = copy.chat.id,
    messageId = copy.messageId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    execute(
        content.createResend(
            chatId = replyInChatId,
            messageThreadId = replyInThreadId,
            directMessageThreadId = replyInDirectMessageThreadId,
            businessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId),
            replyMarkup = replyMarkup,
        )
    )
}

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
)

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLocationChatIdAndMessageId")
@JsName("replyLiveLocationWithLocationChatIdAndMessageId")
public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
        directMessageThreadId = replyInDirectMessageThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
    )
}

/**
 * Will use [handleLiveLocation] with replying to [toMessageId] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLatLongChatIdAndMessageId")
@JsName("replyLiveLocationWithLatLongChatIdAndMessageId")
public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
        directMessageThreadId = replyInDirectMessageThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = ReplyParameters(toChatId, toMessageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId)
    )
}

public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    mediaFile: TelegramMediaFile,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            videoNote = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            sticker = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = mediaFile.asDocumentFile(),
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    content: TextedMediaContent,
    entities: List<TextSource>,
    replyInChatId: IdChatIdentifier = toChatId,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            voice = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            audio = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            photoSize = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            toChatId = toChatId,
            toMessageId = toMessageId,
            video = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            animation = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
            toChatId = toChatId,
            toMessageId = toMessageId,
            document = content.media.asDocumentFile(),
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
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
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    entities: TextSourcesList,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = toChatId,
        starCount = starCount,
        media = media,
        entities = entities,
        payload = payload,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = toMessageId,
            chatIdentifier = toChatId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
        )
    )
}

public suspend fun TelegramBot.reply(
    toChatId: IdChatIdentifier,
    toMessageId: MessageId,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    text: String? = null,
    parseMode: ParseMode? = null,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = toChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    sendPaidMedia(
        chatId = toChatId,
        starCount = starCount,
        media = media,
        text = text,
        parseMode = parseMode,
        payload = payload,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = toMessageId,
            chatIdentifier = toChatId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
        )
    )
}
