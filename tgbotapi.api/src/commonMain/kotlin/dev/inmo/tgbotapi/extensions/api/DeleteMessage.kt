package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent

public suspend fun TelegramBot.deleteMessage(
    chatId: ChatIdentifier,
    messageId: MessageId,
): Boolean =
    execute(
        DeleteMessage(chatId, messageId),
    )

public suspend fun TelegramBot.deleteMessage(
    chat: Chat,
    messageId: MessageId,
): Boolean = deleteMessage(chat.id, messageId)

public suspend fun TelegramBot.deleteMessage(message: AccessibleMessage): Boolean {
    val mediaGroupContent = ((message as? ContentMessage<*>) ?.content as? MediaGroupCollectionContent<*>)
    if (mediaGroupContent == null) {
        return deleteMessage(message.chat, message.messageId)
    } else {
        return mediaGroupContent.group.map {
            deleteMessage(it.sourceMessage)
        }.all { it }
    }
}

public suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageId: MessageId,
): Boolean = deleteMessage(chatId, messageId)

public suspend fun TelegramBot.delete(
    chat: Chat,
    messageId: MessageId,
): Boolean = deleteMessage(chat, messageId)

public suspend fun TelegramBot.delete(message: AccessibleMessage): Boolean = deleteMessage(message)

public suspend fun AccessibleMessage.delete(requestsExecutor: TelegramBot): Boolean = requestsExecutor.deleteMessage(this)
