package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendDocument
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.DocumentContent

/**
 * Sends to [chatId]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = execute(
    SendDocument(
        chatId = chatId,
        document = document,
        thumbnail = thumb,
        text = text,
        parseMode = parseMode,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        receiverUserId = receiverUserId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )
)

/**
 * Sends an ephemeral reply to the user who pressed the button. [ephemeralMessageParameters] must contain a callback
 * query ID and omit the receiver user ID.
 */
public suspend fun TelegramBot.sendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = execute(
    SendDocument(
        ephemeralMessageParameters = ephemeralMessageParameters,
        document = document,
        thumbnail = thumb,
        text = text,
        parseMode = parseMode,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )
)

/** Sends an ephemeral reply to [callbackQueryId]. */
public suspend fun TelegramBot.sendDocument(
    callbackQueryId: CallbackQueryId,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = EphemeralMessageParameters(
        callbackQueryId = callbackQueryId,
        replaceCallbackQueryMessage = replaceCallbackQueryMessage
    ),
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chat]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chat.id,
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chatId]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chatId,
    document = document.fileId,
    thumb = document.thumbnail ?.fileId,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends an ephemeral reply to the user who pressed the button. [ephemeralMessageParameters] must contain a callback
 * query ID and omit the receiver user ID.
 */
public suspend fun TelegramBot.sendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = ephemeralMessageParameters,
    document = document.fileId,
    thumb = document.thumbnail ?.fileId,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/** Sends an ephemeral reply to [callbackQueryId]. */
public suspend fun TelegramBot.sendDocument(
    callbackQueryId: CallbackQueryId,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = EphemeralMessageParameters(
        callbackQueryId = callbackQueryId,
        replaceCallbackQueryMessage = replaceCallbackQueryMessage
    ),
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chat]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chat.id,
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chatId]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = execute(
    SendDocument(
        chatId = chatId,
        document = document,
        thumbnail = thumb,
        entities = entities,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        receiverUserId = receiverUserId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )
)

/**
 * Sends an ephemeral reply to the user who pressed the button. [ephemeralMessageParameters] must contain a callback
 * query ID and omit the receiver user ID.
 */
public suspend inline fun TelegramBot.sendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = execute(
    SendDocument(
        ephemeralMessageParameters = ephemeralMessageParameters,
        document = document,
        thumbnail = thumb,
        entities = entities,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
        disableContentTypeDetection = disableContentTypeDetection
    )
)

/** Sends an ephemeral reply to [callbackQueryId]. */
public suspend inline fun TelegramBot.sendDocument(
    callbackQueryId: CallbackQueryId,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = EphemeralMessageParameters(
        callbackQueryId = callbackQueryId,
        replaceCallbackQueryMessage = replaceCallbackQueryMessage
    ),
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chat]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chat.id,
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chatId]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chatId,
    document = document.fileId,
    thumb = document.thumbnail ?.fileId,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends an ephemeral reply to the user who pressed the button. [ephemeralMessageParameters] must contain a callback
 * query ID and omit the receiver user ID.
 */
public suspend inline fun TelegramBot.sendDocument(
    ephemeralMessageParameters: EphemeralMessageParameters,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = ephemeralMessageParameters,
    document = document.fileId,
    thumb = document.thumbnail ?.fileId,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/** Sends an ephemeral reply to [callbackQueryId]. */
public suspend inline fun TelegramBot.sendDocument(
    callbackQueryId: CallbackQueryId,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    ephemeralMessageParameters = EphemeralMessageParameters(
        callbackQueryId = callbackQueryId,
        replaceCallbackQueryMessage = replaceCallbackQueryMessage
    ),
    document = document,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

/**
 * Sends to [chat]. Set [receiverUserId] to deliver the message only to a particular user.
 *
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chatId = chat.id,
    document = document,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)
