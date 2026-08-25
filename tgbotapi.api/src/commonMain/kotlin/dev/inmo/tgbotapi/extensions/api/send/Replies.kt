@file:Suppress("KDocUnresolvedReference")
@file:JvmName("RepliesKt")
@file:JvmMultifileClass

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
import dev.inmo.tgbotapi.types.message.abstracts.ChatMessage
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.*
import kotlinx.coroutines.flow.Flow
import kotlin.js.JsName
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


// Contact

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ContactContent> = sendContact(
    chatId = replyInChatId,
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = lastName,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    contact: Contact,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ContactContent> = sendContact(
    chatId = replyInChatId,
    contact = contact,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Dice

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyWithDice(
    to: ChatMessage,
    animationType: DiceAnimationType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<DiceContent> = sendDice(
    chatId = replyInChatId,
    animationType = animationType,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    animationType: DiceAnimationType,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<DiceContent> = replyWithDice(
    to = to,
    animationType = animationType,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup
)


// Checklist

public suspend inline fun TelegramBot.replyWithChecklist(
    to: ChatMessage,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
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
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = to.businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    replyInBusinessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyInChatId: IdChatIdentifier = to.chat.id,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
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
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ChecklistContent> = sendChecklist(
    chatId = replyInChatId,
    checklist = checklist,
    businessConnectionId = to.businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Location

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    latitude: Double,
    longitude: Double,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StaticLocationContent> = sendLocation(
    chatId = replyInChatId,
    latitude = latitude,
    longitude = longitude,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    location: StaticLocation,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StaticLocationContent> = sendLocation(
    chatId = replyInChatId,
    location = location,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Text message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<TextContent> = sendTextMessage(
    chatId = replyInChatId,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
): ChatContentMessage<TextContent> = sendTextMessage(
    chatId = replyInChatId,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.reply(
    to: ChatMessage,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ChatContentMessage<TextContent> = reply(
    to = to,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.reply(
    to: ChatMessage,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ChatContentMessage<TextContent> = reply(
    to = to,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup,
)


// Venue

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
): ChatContentMessage<VenueContent> = sendVenue(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    location: StaticLocation,
    title: String,
    address: String,
    foursquareId: FoursquareId? = null,
    foursquareType: FoursquareType? = null,
    googlePlaceId: GooglePlaceId? = null,
    googlePlaceType: GooglePlaceType? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VenueContent> = sendVenue(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    venue: Venue,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VenueContent> = sendVenue(
    chatId = replyInChatId,
    venue = venue,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Game

public suspend inline fun TelegramBot.replyWithGame(
    to: ChatMessage,
    gameShortName: String,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<GameContent> = sendGame(
    chatId = replyInChatId,
    gameShortName = gameShortName,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithGame(
    to: ChatMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<GameContent> = sendGame(
    chatId = replyInChatId,
    gameShortName = game.title,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    game: Game,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<GameContent> = replyWithGame(
    to = to,
    game = game,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup
)


// Rich message

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.replyRichMessage(
    to: ChatMessage,
    richMessage: InputRichMessage,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = sendRichMessage(
    chatId = replyInChatId,
    richMessage = richMessage,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    richMessage: InputRichMessage,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = replyRichMessage(
    to = to,
    richMessage = richMessage,
    replyInChatId = replyInChatId,
    replyInThreadId = replyInThreadId,
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    replyInBusinessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup
)


// Animation

public suspend inline fun TelegramBot.replyWithAnimation(
    to: ChatMessage,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAnimation(
    to: ChatMessage,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    animation: AnimationFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Audio

public suspend inline fun TelegramBot.replyWithAudio(
    to: ChatMessage,
    audio: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    audio: AudioFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    text = text,
    parseMode = parseMode,
    title = title,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithAudio(
    to: ChatMessage,
    audio: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    audio: AudioFile,
    entities: TextSourcesList,
    title: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
    chatId = replyInChatId,
    audio = audio,
    entities = entities,
    title = title,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Documents

public suspend inline fun TelegramBot.replyWithDocument(
    to: ChatMessage,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.replyWithDocument(
    to: ChatMessage,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    document: DocumentFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = replyInChatId,
    document = document,
    entities = entities,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)


// Media Group

@RiskFeature(rawSendingMediaGroupsWarning)
public suspend inline fun TelegramBot.replyWithMediaGroup(
    to: ChatMessage,
    media: List<MediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
): ChatContentMessage<MediaGroupContent<MediaGroupPartContent>> = sendMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
)

public suspend inline fun TelegramBot.replyWithPlaylist(
    to: ChatMessage,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
): ChatContentMessage<MediaGroupContent<AudioContent>> = sendPlaylist(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
)

public suspend inline fun TelegramBot.replyWithDocuments(
    to: ChatMessage,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
): ChatContentMessage<MediaGroupContent<DocumentContent>> = sendDocumentsGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
)

public suspend inline fun TelegramBot.replyWithGallery(
    to: ChatMessage,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
): ChatContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = sendVisualMediaGroup(
    chatId = replyInChatId,
    media = media,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        metaInfo = to.metaInfo,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
)


// Photo

public suspend inline fun TelegramBot.replyWithPhoto(
    to: ChatMessage,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithPhoto(
    to: ChatMessage,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = replyInChatId,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Sticker

public suspend inline fun TelegramBot.replyWithSticker(
    to: ChatMessage,
    sticker: InputFile,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = replyInChatId,
    sticker = sticker,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    sticker: Sticker,
    emoji: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = replyInChatId,
    sticker = sticker,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Videos

public suspend inline fun TelegramBot.replyWithVideo(
    to: ChatMessage,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = replyInChatId,
    video = video,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
    duration = duration,
    width = width,
    height = height,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = replyInChatId,
    video = video,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithVideo(
    to: ChatMessage,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = replyInChatId,
    video = video,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = replyInChatId,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply == true,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// Live Photos

public suspend inline fun TelegramBot.replyWithLivePhoto(
    to: ChatMessage,
    livePhoto: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    chatId = replyInChatId,
    livePhoto = livePhoto,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    livePhoto: LivePhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    chatId = replyInChatId,
    livePhoto = livePhoto,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.replyWithLivePhoto(
    to: ChatMessage,
    livePhoto: InputFile,
    photo: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    chatId = replyInChatId,
    livePhoto = livePhoto,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    livePhoto: LivePhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    chatId = replyInChatId,
    livePhoto = livePhoto,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(
        message = to,
        allowSendingWithoutReply = allowSendingWithoutReply == true,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)


// VideoNotes

public suspend inline fun TelegramBot.replyWithVideoNote(
    to: ChatMessage,
    videoNote: InputFile,
    thumb: InputFile? = null,
    duration: Long? = null,
    size: Int? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoNoteContent> = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    thumb = thumb,
    duration = duration,
    size = size,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    videoNote: VideoNoteFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoNoteContent> = sendVideoNote(
    chatId = replyInChatId,
    videoNote = videoNote,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


// Voice

public suspend inline fun TelegramBot.replyWithVoice(
    to: ChatMessage,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    text = text,
    parseMode = parseMode,
    duration = duration,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    voice: VoiceFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    text = text,
    parseMode = parseMode,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.replyWithVoice(
    to: ChatMessage,
    voice: InputFile,
    entities: TextSourcesList,
    duration: Long? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    entities = entities,
    duration = duration,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    voice: VoiceFile,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    chatId = replyInChatId,
    voice = voice,
    entities = entities,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = to.ephemeralReplyParametersOrNull(
        ephemeralMessageId = replyToEphemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId,
    ) ?: ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


// Invoice

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
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
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ChatContentMessage<InvoiceContent> = sendInvoice(
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
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    title: String,
    description: String,
    payload: String,
    price: LabeledPrice,
    startParameter: StartParameter? = null,
    providerData: String? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ChatContentMessage<InvoiceContent> = sendInvoice(
    chatId = replyInChatId,
    title = title,
    description = description,
    payload = payload,
    price = price,
    startParameter = startParameter,
    providerData = providerData,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


// Polls


public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    question: String,
    options: List<InputPollOption>,
    questionParseMode: ParseMode? = null,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    poll: RegularPoll,
    question: String,
    questionParseMode: ParseMode? = null,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = poll.allowsMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    isAnonymous = isAnonymous,
    isClosed = allowsMultipleAnswers,
    allowsMultipleAnswers = isClosed,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    closeInfo = closeInfo,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    poll: RegularPoll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = poll.allowsMultipleAnswers,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendRegularPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    closeInfo = closeInfo,
    isAnonymous = isAnonymous,
    isClosed = allowsMultipleAnswers,
    allowsMultipleAnswers = isClosed,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    question: String,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    quizPoll: QuizPoll,
    question: String,
    explanation: String?,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionIds: List<Int> = quizPoll.correctOptionIds
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = quizPoll.allowsMultipleAnswers,
    allowsRevoting: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    explanation: String?,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    quizPoll: QuizPoll,
    explanation: String?,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionIds: List<Int> = quizPoll.correctOptionIds
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = quizPoll.allowsMultipleAnswers,
    allowsRevoting: Boolean = false,
    explanationParseMode: ParseMode? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    explanation = explanation,
    explanationParseMode = explanationParseMode,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    question: String,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    quizPoll: QuizPoll,
    question: String,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionIds: List<Int> = quizPoll.correctOptionIds
        ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = quizPoll.allowsMultipleAnswers,
    allowsRevoting: Boolean = false,
    questionParseMode: ParseMode? = null,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    question = question,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    questionParseMode = questionParseMode,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    questionTextSources: List<TextSource>,
    options: List<InputPollOption>,
    correctOptionIds: List<Int>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = false,
    allowsRevoting: Boolean = false,
    explanationTextSources: List<TextSource>? = null,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    quizPoll: QuizPoll,
    questionTextSources: List<TextSource> = quizPoll.questionTextSources,
    options: List<InputPollOption> = quizPoll.options.map { it.asInput() },
    correctOptionIds: List<Int> = quizPoll.correctOptionIds ?: error("Correct option ID must be provided by income QuizPoll or by developer"),
    isAnonymous: Boolean = quizPoll.isAnonymous,
    isClosed: Boolean = false,
    allowsMultipleAnswers: Boolean = quizPoll.allowsMultipleAnswers,
    allowsRevoting: Boolean = false,
    explanationTextSources: List<TextSource>? = quizPoll.explanationTextSources,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = sendQuizPoll(
    chatId = replyInChatId,
    questionEntities = questionTextSources,
    options = options,
    correctOptionIds = correctOptionIds,
    closeInfo = closeInfo,
    explanationTextSources = explanationTextSources,
    isAnonymous = isAnonymous,
    isClosed = isClosed,
    allowsMultipleAnswers = allowsMultipleAnswers,
    allowsRevoting = allowsRevoting,
    threadId = replyInThreadId,
    directMessageThreadId = replyInDirectMessageThreadId,
    businessConnectionId = replyInBusinessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.chat.id, to.messageId, allowSendingWithoutReply = allowSendingWithoutReply, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)


public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    poll: Poll,
    question: String,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = when (poll) {
    is RegularPoll -> reply(
        toChatId = to.chat.id,
        toMessageId = to.messageId,
        poll = poll,
        isClosed = isClosed,
        question = question,
        options = options,
        isAnonymous = isAnonymous,
        allowsMultipleAnswers = isAnonymous,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
        replyMarkup = replyMarkup
    )
}
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    poll: Poll,
    questionTextSources: List<TextSource> = poll.questionTextSources,
    options: List<InputPollOption> = poll.options.map { it.asInput() },
    isAnonymous: Boolean = poll.isAnonymous,
    isClosed: Boolean = false,
    closeInfo: ScheduledCloseInfo? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PollContent> = when (poll) {
    is RegularPoll -> reply(
        toChatId = to.chat.id,
        toMessageId = to.messageId,
        poll = poll,
        questionTextSources = questionTextSources,
        options = options,
        isAnonymous = isAnonymous,
        isClosed = isClosed,
        allowsMultipleAnswers = isAnonymous,
        closeInfo = closeInfo,
        replyInChatId = replyInChatId,
        replyInThreadId = replyInThreadId,
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
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
        replyInDirectMessageThreadId = replyInDirectMessageThreadId,
        replyInBusinessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
        replyMarkup = replyMarkup
    )
}
public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to.metaInfo, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.reply(
    to: ChatMessage,
    copy: ChatMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
    replyInDirectMessageThreadId = replyInDirectMessageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    allowSendingWithoutReply = allowSendingWithoutReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    content: MessageContent,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null,
): ChatMessage = execute(
    content.createResend(
        chatId = replyInChatId,
        messageThreadId = replyInThreadId,
        directMessageThreadId = replyInDirectMessageThreadId,
        businessConnectionId = replyInBusinessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId),
        replyMarkup = replyMarkup,
    )
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
public suspend fun TelegramBot.reply(
    to: ChatMessage,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId)
)

/**
 * Will use [handleLiveLocation] with replying to [to] each time new message will be sent by live location update
 *
 * @see handleLiveLocation
 */
@JvmName("replyLiveLocationWithLocation")
@JsName("replyLiveLocationWithLocation")
public suspend fun TelegramBot.reply(
    to: ChatMessage,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId)
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
    to: ChatMessage,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = ReplyParameters(to, allowSendingWithoutReply = allowSendingWithoutReply == true, checklistTaskId = checklistTaskId, pollOptionId = pollOptionId)
    )
}

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    mediaFile: TelegramMediaFile,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (mediaFile) {
        is AudioFile -> reply(
            to = to,
            audio = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is AnimationFile -> reply(
            to = to,
            animation = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is VoiceFile -> reply(
            to = to,
            voice = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is VideoFile -> reply(
            to = to,
            video = mediaFile,
            supportsStreaming = false,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is LivePhotoFile -> reply(
            to = to,
            livePhoto = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is VideoNoteFile -> reply(
            to = to,
            videoNote = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is DocumentFile -> reply(
            to = to,
            document = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is Sticker -> reply(
            to = to,
            sticker = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is PhotoSize -> reply(
            to = to,
            photoSize = mediaFile,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        else -> reply(
            to = to,
            document = mediaFile.asDocumentFile(),
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    content: TextedMediaContent,
    text: String?,
    parseMode: ParseMode? = null,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            to = to,
            audio = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            to = to,
            photoSize = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            to = to,
            video = content.media,
            text = text,
            supportsStreaming = false,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is LivePhotoContent -> reply(
            to = to,
            livePhoto = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            to = to,
            animation = content.media,
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        else -> reply(
            to = to,
            document = content.media.asDocumentFile(),
            text = text,
            parseMode = parseMode,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    content: TextedMediaContent,
    entities: TextSourcesList,
    replyInChatId: IdChatIdentifier = to.chat.id,
    replyInThreadId: MessageThreadId? = replyInChatId.threadId,
    replyInDirectMessageThreadId: DirectMessageThreadId? = replyInChatId.directMessageThreadId,
    replyInBusinessConnectionId: BusinessConnectionId? = replyInChatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = (replyInChatId.receiverUser ?: to.ephemeralReplyReceiverUserIdOrNull) ?.let(::EphemeralMessageParameters),
    replyToEphemeralMessageId: EphemeralMessageId? = (to as? PossiblyEphemeralMessage) ?.ephemeralMessageId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
) {
    when (content) {
        is VoiceContent -> reply(
            to = to,
            voice = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is AudioMediaGroupPartContent -> reply(
            to = to,
            audio = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is PhotoContent -> reply(
            to = to,
            photoSize = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is VideoContent -> reply(
            to = to,
            video = content.media,
            supportsStreaming = false,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is LivePhotoContent -> reply(
            to = to,
            livePhoto = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        is AnimationContent -> reply(
            to = to,
            animation = content.media,
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
        else -> reply(
            to = to,
            document = content.media.asDocumentFile(),
            entities = entities,
            replyInChatId = replyInChatId,
            replyInThreadId = replyInThreadId,
            replyInDirectMessageThreadId = replyInDirectMessageThreadId,
            replyInBusinessConnectionId = replyInBusinessConnectionId,
            ephemeralMessageParameters = ephemeralMessageParameters,
    replyToEphemeralMessageId = replyToEphemeralMessageId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            effectId = effectId,
            suggestedPostParameters = suggestedPostParameters,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
            replyMarkup = replyMarkup
        )
    }
}

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    entities: TextSourcesList,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = to.chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        suggestedPostParameters = suggestedPostParameters,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
        )
    )
}

public suspend fun TelegramBot.reply(
    to: ChatMessage,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    text: String? = null,
    parseMode: ParseMode? = null,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = to.chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = to.chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = to.chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
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
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        suggestedPostParameters = suggestedPostParameters,
        replyMarkup = replyMarkup,
        replyParameters = ReplyParameters(
            messageId = to.messageId,
            chatIdentifier = to.chat.id,
            allowSendingWithoutReply = allowSendingWithoutReply,
            checklistTaskId = checklistTaskId,
            pollOptionId = pollOptionId
        )
    )
}
