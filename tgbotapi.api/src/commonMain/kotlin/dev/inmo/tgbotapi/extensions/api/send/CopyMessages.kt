package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.media.sendMediaGroup
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    messages: List<MediaGroupMessage<MediaGroupContent>>,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
): List<MediaGroupMessage<MediaGroupContent>> {
    val first = messages.first().content.toMediaGroupMemberTelegramMedia().let {
        if (text != null) {
            when (it) {
                is TelegramMediaAudio -> it.copy(text = text, parseMode = parseMode)
                is TelegramMediaDocument -> it.copy(text = text, parseMode = parseMode)
                is TelegramMediaPhoto -> it.copy(text = text, parseMode = parseMode)
                is TelegramMediaVideo -> it.copy(text = text, parseMode = parseMode)
            }
        } else {
            it
        }
    }

    return sendMediaGroup(
        toChatId,
        listOf(first) + messages.drop(1).map {
            it.content.toMediaGroupMemberTelegramMedia()
        },
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )
}

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: Chat,
    messages: List<MediaGroupMessage<MediaGroupContent>>,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat.id, messages, text, parseMode, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: ChatIdentifier,
    update: SentMediaGroupUpdate,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat, update.data, text, parseMode, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: Chat,
    update: SentMediaGroupUpdate,
    text: String? = null,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat.id, update, text, parseMode, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    messages: List<MediaGroupMessage<MediaGroupContent>>,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
): List<MediaGroupMessage<MediaGroupContent>> {
    val first = messages.first().content.toMediaGroupMemberTelegramMedia().let {
        when (it) {
            is TelegramMediaAudio -> TelegramMediaAudio(it.file, entities, it.duration, it.performer, it.title, it.thumb)
            is TelegramMediaDocument -> TelegramMediaDocument(it.file, entities, it.thumb, it.disableContentTypeDetection)
            is TelegramMediaPhoto -> TelegramMediaPhoto(it.file, entities)
            is TelegramMediaVideo -> TelegramMediaVideo(it.file, entities, it.width, it.height, it.duration, it.thumb)
        }
    }

    return sendMediaGroup(
        toChatId,
        listOf(first) + messages.drop(1).map {
            it.content.toMediaGroupMemberTelegramMedia()
        },
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )
}

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: Chat,
    messages: List<MediaGroupMessage<MediaGroupContent>>,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat.id, messages, entities, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: ChatIdentifier,
    update: SentMediaGroupUpdate,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat, update.data, entities, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Send media group via [sendMediaGroup] extension with edited [entities] of first [messages] element. Other elements
 * will be copied as they are
 */
suspend inline fun TelegramBot.copyMessages(
    toChat: Chat,
    update: SentMediaGroupUpdate,
    entities: TextSourcesList,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = copyMessages(toChat.id, update, entities, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)
