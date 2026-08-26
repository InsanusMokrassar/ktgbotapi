@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendVideo
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.VideoContent

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = execute(
    SendVideo(
        chatId = chatId,
        video = video,
        thumbnail = thumb,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        cover = cover,
        startTimestamp = startTimestamp,
        duration = duration,
        width = width,
        height = height,
        supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chatId = chatId, video = video, thumb = thumb, text = text, parseMode = parseMode, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, cover = cover, startTimestamp = startTimestamp, duration = duration, width = width, height = height, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video.fileId,
    thumb = video.thumbnail ?.fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = video.cover ?.fileId,
    startTimestamp = video.startTimestamp,
    duration = video.duration,
    width = video.width,
    height = video.height,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chatId = chatId, video = video, text = text, parseMode = parseMode, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chat.id,
    video = video,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chat = chat, video = video, thumb = thumb, text = text, parseMode = parseMode, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, cover = cover, startTimestamp = startTimestamp, duration = duration, width = width, height = height, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chat = chat,
    video = video,
    thumb = thumb,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chat.id,
    video = video,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chat = chat, video = video, text = text, parseMode = parseMode, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chat = chat,
    video = video,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = execute(
    SendVideo(
        chatId = chatId,
        video = video,
        thumbnail = thumb,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        cover = cover,
        startTimestamp = startTimestamp,
        duration = duration,
        width = width,
        height = height,
        supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chatId = chatId, video = video, thumb = thumb, entities = entities, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, cover = cover, startTimestamp = startTimestamp, duration = duration, width = width, height = height, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video.fileId,
    thumb = video.thumbnail ?.fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = video.cover ?.fileId,
    startTimestamp = video.startTimestamp,
    duration = video.duration,
    width = video.width,
    height = video.height,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chatId = chatId, video = video, entities = entities, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendVideo(
    chatId: ChatIdentifier,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chatId,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chat.id,
    video = video,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chat = chat, video = video, thumb = thumb, entities = entities, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, cover = cover, startTimestamp = startTimestamp, duration = duration, width = width, height = height, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: InputFile,
    thumb: InputFile? = null,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    cover: InputFile? = null,
    startTimestamp: Seconds? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chat = chat,
    video = video,
    thumb = thumb,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    cover = cover,
    startTimestamp = startTimestamp,
    duration = duration,
    width = width,
    height = height,
    supportsStreaming = supportsStreaming,
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
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
): ChatContentMessage<VideoContent> = sendVideo(
    chatId = chat.id,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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

@Deprecated(
    "Use ephemeralMessageParameters instead",
    ReplaceWith(
        "sendVideo(chat = chat, video = video, entities = entities, showCaptionAboveMedia = showCaptionAboveMedia, spoilered = spoilered, supportsStreaming = supportsStreaming, threadId = threadId, directMessageThreadId = directMessageThreadId, businessConnectionId = businessConnectionId, ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId) }, disableNotification = disableNotification, protectContent = protectContent, allowPaidBroadcast = allowPaidBroadcast, effectId = effectId, suggestedPostParameters = suggestedPostParameters, replyParameters = replyParameters, replyMarkup = replyMarkup)",
        imports = ["dev.inmo.tgbotapi.types.EphemeralMessageParameters"]
    )
)
public suspend inline fun TelegramBot.sendVideo(
    chat: Chat,
    video: VideoFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    supportsStreaming: Boolean = false,
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
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<VideoContent> = sendVideo(
    chat = chat,
    video = video,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    supportsStreaming = supportsStreaming,
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
)
