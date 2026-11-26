@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.*
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.polls.PollOptionPersistentId
import dev.inmo.tgbotapi.types.rich.InputRichMessage

/**
 * Replies to an incoming [ephemeralMessageId] in [chatId] and delivers the response to [receiverUserId].
 * Overloads taking [EphemeralMessageParameters] derive the destination from a callback query.
 * Overloads taking [EphemeralChatId] use its embedded receiver and ephemeral message ID.
 *
 * @see dev.inmo.tgbotapi.types.ephemeralReplyParametersOrNull
 */

public suspend fun TelegramBot.replyToEphemeralRichMessage(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
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
    chatId = chatId,
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        ephemeralMessageId = ephemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralRichMessage(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
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
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = ReplyParameters(
        ephemeralMessageId = ephemeralMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    ),
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.replyToEphemeralRichMessage(
    chatId: EphemeralChatId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = replyToEphemeralRichMessage(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
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

public suspend fun TelegramBot.replyToEphemeral(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = replyToEphemeralRichMessage(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
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

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeral(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = replyToEphemeralRichMessage(
    ephemeralMessageParameters = ephemeralMessageParameters,
    ephemeralMessageId = ephemeralMessageId,
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
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

public suspend fun TelegramBot.replyToEphemeral(
    chatId: EphemeralChatId,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    allowSendingWithoutReply: Boolean? = null,
    checklistTaskId: ChecklistTaskId? = null,
    pollOptionId: PollOptionPersistentId? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<RichMessageContent> = replyToEphemeralRichMessage(
    chatId = chatId,
    richMessage = richMessage,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeral(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<TextContent> = sendMessage(
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.replyToEphemeral(
    chatId: EphemeralChatId,
    text: String,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<TextContent> = replyToEphemeral(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithPhoto(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    fileId = photo,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithPhoto(
    chatId: EphemeralChatId,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = replyToEphemeralWithPhoto(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    photo = photo,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithLivePhoto(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    livePhoto: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = sendLivePhoto(
    livePhoto = livePhoto,
    photo = photo,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithLivePhoto(
    chatId: EphemeralChatId,
    livePhoto: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<LivePhotoContent> = replyToEphemeralWithLivePhoto(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    livePhoto = livePhoto,
    photo = photo,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithAudio(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    audio: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = sendAudio(
    audio = audio,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithAudio(
    chatId: EphemeralChatId,
    audio: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AudioContent> = replyToEphemeralWithAudio(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    audio = audio,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    document: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    document = document,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithDocument(
    chatId: EphemeralChatId,
    document: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<DocumentContent> = replyToEphemeralWithDocument(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    document = document,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithVideo(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    video: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    video = video,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithVideo(
    chatId: EphemeralChatId,
    video: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = replyToEphemeralWithVideo(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    video = video,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithAnimation(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    animation: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = sendAnimation(
    animation = animation,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithAnimation(
    chatId: EphemeralChatId,
    animation: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<AnimationContent> = replyToEphemeralWithAnimation(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    animation = animation,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithVoice(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = sendVoice(
    voice = voice,
    text = text,
    parseMode = parseMode,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithVoice(
    chatId: EphemeralChatId,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VoiceContent> = replyToEphemeralWithVoice(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    voice = voice,
    text = text,
    parseMode = parseMode,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithVideoNote(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    videoNote: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoNoteContent> = sendVideoNote(
    videoNote = videoNote,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithVideoNote(
    chatId: EphemeralChatId,
    videoNote: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoNoteContent> = replyToEphemeralWithVideoNote(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    videoNote = videoNote,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithSticker(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    sticker: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    sticker = sticker,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithSticker(
    chatId: EphemeralChatId,
    sticker: InputFile,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = replyToEphemeralWithSticker(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    sticker = sticker,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithLocation(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    latitude: Double,
    longitude: Double,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StaticLocationContent> = sendStaticLocation(
    latitude = latitude,
    longitude = longitude,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Sends a static location (`live_period` is not supported for ephemeral messages, see [SendLocation.Live]).
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithLocation(
    chatId: EphemeralChatId,
    latitude: Double,
    longitude: Double,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StaticLocationContent> = replyToEphemeralWithLocation(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    latitude = latitude,
    longitude = longitude,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithVenue(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VenueContent> = sendVenue(
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithVenue(
    chatId: EphemeralChatId,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VenueContent> = replyToEphemeralWithVenue(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    latitude = latitude,
    longitude = longitude,
    title = title,
    address = address,
    allowSendingWithoutReply = allowSendingWithoutReply,
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
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/** Replies to the source message using the callback in [ephemeralMessageParameters]. */
public suspend fun TelegramBot.replyToEphemeralWithContact(
    ephemeralMessageParameters: EphemeralMessageParameters,
    ephemeralMessageId: EphemeralMessageId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ContactContent> = sendContact(
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = lastName,
    ephemeralMessageParameters = ephemeralMessageParameters,
    replyParameters = ReplyParameters.Ephemeral(ephemeralMessageId, allowSendingWithoutReply),
    replyMarkup = replyMarkup
)

/**
 * Uses the receiver and [EphemeralMessageId] carried by [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.replyToEphemeralWithContact(
    chatId: EphemeralChatId,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<ContactContent> = replyToEphemeralWithContact(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = lastName,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)
