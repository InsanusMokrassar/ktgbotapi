@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.PhotoContent

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = execute(
    SendPhoto(
        chatId = chatId,
        photo = fileId,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = photo.biggest() ?.fileId ?: error("Photo content must not be empty"),
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: PhotoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = photoSize.fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    photoSize: PhotoSize,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = execute(
    SendPhoto(
        chatId = chatId,
        photo = fileId,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
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
        replyMarkup = replyMarkup
    )
)

public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = photo.biggest() ?.fileId ?: error("Photo content must not be empty"),
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    photo: PhotoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    fileId = photoSize.fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chatId,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chatId = chat.id,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
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
    replyMarkup = replyMarkup
)

public suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    photoSize: PhotoSize,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<PhotoContent> = sendPhoto(
    chat = chat,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)
