package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.CopyMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(
        fromChatId,
        messageId,
        toChatId,
        text,
        parseMode,
        showCaptionAboveMedia,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, messageId, toChatId, text, parseMode, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChat: Chat,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChatId, messageId, toChat.id, text, parseMode, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChat: Chat,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, messageId, toChat.id, text, parseMode, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(
        fromChatId,
        messageId,
        toChatId,
        entities,
        showCaptionAboveMedia,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, messageId, toChatId, entities, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChat: Chat,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChatId, messageId, toChat.id, entities, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChat: Chat,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(fromChat.id, messageId, toChat.id, entities, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, message.messageId, toChatId, text, parseMode, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, message.messageId, toChat, text, parseMode, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, message.messageId, toChatId, entities, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: AccessibleMessage,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(message.chat, message.messageId, toChat, entities, showCaptionAboveMedia, threadId, disableNotification, protectContent, replyParameters, replyMarkup)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(
        fromChatId,
        messageId,
        toChatId,
        text,
        parseMode,
        showCaptionAboveMedia,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChatId,
    fromChat.id,
    messageId,
    text,
    parseMode,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChat.id,
    fromChatId,
    messageId,
    text,
    parseMode,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    fromChat: Chat,
    messageId: MessageId,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChat.id,
    fromChat.id,
    messageId,
    text,
    parseMode,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    CopyMessage(
        fromChatId,
        messageId,
        toChatId,
        entities,
        showCaptionAboveMedia,
        threadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    fromChat: Chat,
    messageId: MessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChatId,
    fromChat.id,
    messageId,
    entities,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChat.id,
    fromChatId,
    messageId,
    entities,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    fromChat: Chat,
    messageId: MessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = copyMessage(
    toChat.id,
    fromChat.id,
    messageId,
    entities,
    showCaptionAboveMedia,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
)
