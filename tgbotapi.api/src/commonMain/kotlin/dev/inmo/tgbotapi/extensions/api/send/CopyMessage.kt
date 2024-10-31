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
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChatId: ChatIdentifier,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    text: String? = null,
//    parseMode: ParseMode? = null,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = execute(
//    CopyMessage(
//        fromChatId,
//        messageId,
//        toChatId,
//        text,
//        parseMode,
//        showCaptionAboveMedia,
//        threadId,
//        disableNotification,
//        protectContent,
//        allowPaidBroadcast,
//        replyParameters,
//        replyMarkup
//    )
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChatId: ChatIdentifier,
//    fromChat: Chat,
//    messageId: MessageId,
//    text: String? = null,
//    parseMode: ParseMode? = null,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChatId,
//    fromChat.id,
//    messageId,
//    text,
//    parseMode,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChat: Chat,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    text: String? = null,
//    parseMode: ParseMode? = null,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChat.id.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChat.id,
//    fromChatId,
//    messageId,
//    text,
//    parseMode,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChat: Chat,
//    fromChat: Chat,
//    messageId: MessageId,
//    text: String? = null,
//    parseMode: ParseMode? = null,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChat.id.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChat.id,
//    fromChat.id,
//    messageId,
//    text,
//    parseMode,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
//
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChatId: ChatIdentifier,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    entities: TextSourcesList,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = execute(
//    CopyMessage(
//        fromChatId,
//        messageId,
//        toChatId,
//        entities,
//        showCaptionAboveMedia,
//        threadId,
//        disableNotification,
//        protectContent,
//        allowPaidBroadcast,
//        replyParameters,
//        replyMarkup
//    )
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChatId: ChatIdentifier,
//    fromChat: Chat,
//    messageId: MessageId,
//    entities: TextSourcesList,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChatId.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChatId,
//    fromChat.id,
//    messageId,
//    entities,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChat: Chat,
//    fromChatId: ChatIdentifier,
//    messageId: MessageId,
//    entities: TextSourcesList,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChat.id.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChat.id,
//    fromChatId,
//    messageId,
//    entities,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
//
///**
// * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
// * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
// */
//public suspend inline fun TelegramBot.copyMessage(
//    toChat: Chat,
//    fromChat: Chat,
//    messageId: MessageId,
//    entities: TextSourcesList,
//    showCaptionAboveMedia: Boolean = false,
//    threadId: MessageThreadId? = toChat.id.threadId,
//    disableNotification: Boolean = false,
//    protectContent: Boolean = false,
//    allowPaidBroadcast: Boolean = false,
//    replyParameters: ReplyParameters? = null,
//    replyMarkup: KeyboardMarkup? = null
//): MessageId = copyMessage(
//    toChat.id,
//    fromChat.id,
//    messageId,
//    entities,
//    showCaptionAboveMedia,
//    threadId,
//    disableNotification,
//    protectContent,
//    allowPaidBroadcast,
//    replyParameters,
//    replyMarkup
//)
