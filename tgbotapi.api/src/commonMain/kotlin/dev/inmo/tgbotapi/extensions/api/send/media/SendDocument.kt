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
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
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
        ephemeralMessageParameters = ephemeralMessageParameters,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chatId = chatId, document = document, thumb = thumb, text = text, parseMode = parseMode, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
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
    callbackQueryId: CallbackQueryId? = null,
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
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chat = chat, document = document, thumb = thumb, text = text, parseMode = parseMode, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
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
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chat = chat,
    document = document,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chatId = chatId, document = document, text = text, parseMode = parseMode, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
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
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chat = chat, document = document, text = text, parseMode = parseMode, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chat = chat,
    document = document,
    text = text,
    parseMode = parseMode,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
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
        ephemeralMessageParameters = ephemeralMessageParameters,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chatId = chatId, document = document, thumb = thumb, entities = entities, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
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
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chat = chat, document = document, thumb = thumb, entities = entities, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chat = chat,
    document = document,
    thumb = thumb,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chatId = chatId, document = document, entities = entities, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendDocument(
    chatId: ChatIdentifier,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
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
    document = document,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)

/**
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
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
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
    ephemeralMessageParameters = ephemeralMessageParameters,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection
)

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendDocument(chat = chat, document = document, entities = entities, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup, disableContentTypeDetection = disableContentTypeDetection)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendDocument(
    chat: Chat,
    document: DocumentFile,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    disableContentTypeDetection: Boolean? = null
): ChatContentMessage<DocumentContent> = sendDocument(
    chat = chat,
    document = document,
    entities = entities,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
    disableContentTypeDetection = disableContentTypeDetection,
)
