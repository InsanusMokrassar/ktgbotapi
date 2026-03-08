package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent

public suspend fun TelegramBot.deleteMessage(
    chatId: ChatIdentifier,
    messageId: MessageId
): Unit = execute(
    DeleteMessage(chatId, messageId)
)

public suspend fun TelegramBot.deleteMessage(
    chat: Chat,
    messageId: MessageId
): Unit = deleteMessage(chat.id, messageId)

public suspend fun TelegramBot.deleteMessage(
    message: AccessibleMessage
): Unit {
    val mediaGroupContent = ((message as? ContentMessage<*>) ?.content as? MediaGroupCollectionContent<*>)
    if (mediaGroupContent == null) {
        deleteMessage(message.chat, message.messageId)
    } else {
        mediaGroupContent.group.forEach {
            deleteMessage(it.sourceMessage)
        }
    }
}

public suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageId: MessageId
): Unit = deleteMessage(chatId, messageId)

public suspend fun TelegramBot.delete(
    chat: Chat,
    messageId: MessageId
): Unit = deleteMessage(chat, messageId)

public suspend fun TelegramBot.delete(
    message: AccessibleMessage
): Unit = deleteMessage(message)

public suspend fun AccessibleMessage.delete(
    requestsExecutor: TelegramBot
): Unit = requestsExecutor.deleteMessage(this)
