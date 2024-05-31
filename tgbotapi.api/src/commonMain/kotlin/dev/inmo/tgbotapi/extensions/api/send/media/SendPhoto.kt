package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.*

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
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
) = execute(
    SendPhoto(
        chatId = chatId,
        photo = fileId,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
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
) = sendPhoto(
    chatId = chat.id,
    fileId = fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chatId,
    fileId = photo.biggest() ?.fileId ?: error("Photo content must not be empty"),
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chat.id,
    photo = photo,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chatId,
    fileId = photoSize.fileId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chat.id,
    photoSize = photoSize,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
    chatId: ChatIdentifier,
    fileId: InputFile,
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
) = execute(
    SendPhoto(
        chatId = chatId,
        photo = fileId,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
    chat: Chat,
    fileId: InputFile,
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
) = sendPhoto(
    chatId = chat.id,
    fileId = fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chatId,
    fileId = photo.biggest() ?.fileId ?: error("Photo content must not be empty"),
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chat.id,
    photo = photo,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chatId,
    fileId = photoSize.fileId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.sendPhoto(
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
) = sendPhoto(
    chatId = chat.id,
    photoSize = photoSize,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    spoilered = spoilered,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
