@file:Suppress("KDocUnresolvedReference")

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
public suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = execute(
    CopyMessage(
        fromChatId = fromChatId,
        messageId = messageId,
        toChatId = toChatId,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        startTimestamp = startTimestamp,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChat.id,
    messageId = messageId,
    toChatId = toChatId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChat: Chat,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChatId,
    messageId = messageId,
    toChatId = toChat.id,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChat: Chat,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChat.id,
    messageId = messageId,
    toChatId = toChat.id,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)


/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = execute(
    CopyMessage(
        fromChatId = fromChatId,
        messageId = messageId,
        toChatId = toChatId,
        entities = entities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        startTimestamp = startTimestamp,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChatId: ChatIdentifier,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChat.id,
    messageId = messageId,
    toChatId = toChatId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChatId: ChatIdentifier,
    messageId: MessageId,
    toChat: Chat,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChatId,
    messageId = messageId,
    toChatId = toChat.id,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    fromChat: Chat,
    messageId: MessageId,
    toChat: Chat,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChatId = fromChat.id,
    messageId = messageId,
    toChatId = toChat.id,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChat = message.chat,
    messageId = message.messageId,
    toChatId = toChatId,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: AccessibleMessage,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChat = message.chat,
    messageId = message.messageId,
    toChat = toChat,
    text = text,
    parseMode = parseMode,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChat = message.chat,
    messageId = message.messageId,
    toChatId = toChatId,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend inline fun TelegramBot.copyMessage(
    toChat: Chat,
    message: AccessibleMessage,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): MessageId = copyMessage(
    fromChat = message.chat,
    messageId = message.messageId,
    toChat = toChat,
    entities = entities,
    showCaptionAboveMedia = showCaptionAboveMedia,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
