package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.threadId

/**
 * This method will send [content] to the [chatId] as is
 */
suspend inline fun <T : MessageContent> TelegramBot.resend(
    chatId: ChatIdentifier,
    content: T,
    messageThreadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    content.createResend(
        chatId = chatId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
) as ContentMessage<T>

/**
 * This method will send [content] to the [chatId] as is
 */
suspend inline fun <T : MessageContent> TelegramBot.resend(
    chat: Chat,
    content: T,
    messageThreadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = resend(
    chatId = chat.id,
    content = content,
    messageThreadId = messageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = replyToMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

/**
 * This method will send [message] content to the [chatId]. In difference with [copyMessage], this method will use
 * native methods for data sending (like [dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto] if inoming content is
 * [dev.inmo.tgbotapi.types.message.content.PhotoContent])
 */
suspend inline fun <T : MessageContent> TelegramBot.resend(
    chatId: ChatIdentifier,
    message: ContentMessage<T>,
    messageThreadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = resend(
    chatId = chatId,
    content = message.content,
    messageThreadId = messageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = replyToMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)

/**
 * This method will send [message] content to the [chat]. In difference with [copyMessage], this method will use
 * native methods for data sending (like [dev.inmo.tgbotapi.extensions.api.send.media.sendPhoto] if inoming content is
 * [dev.inmo.tgbotapi.types.message.content.PhotoContent])
 */
suspend inline fun <T : MessageContent> TelegramBot.resend(
    chat: Chat,
    message: ContentMessage<T>,
    messageThreadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
) = resend(
    chatId = chat.id,
    message = message,
    messageThreadId = messageThreadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    replyToMessageId = replyToMessageId,
    allowSendingWithoutReply = allowSendingWithoutReply,
    replyMarkup = replyMarkup
)
