package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent

suspend fun TelegramBot.deleteMessage(
    chatId: ChatIdentifier,
    messageId: MessageId
) = execute(
    DeleteMessage(chatId, messageId)
)

suspend fun TelegramBot.deleteMessage(
    chat: Chat,
    messageId: MessageId
) = deleteMessage(chat.id, messageId)

suspend fun TelegramBot.deleteMessage(
    message: Message
): Boolean {
    val mediaGroupContent = ((message as? ContentMessage<*>) ?.content as? MediaGroupCollectionContent<*>)
    if (mediaGroupContent == null) {
        return deleteMessage(message.chat, message.messageId)
    } else {
        return mediaGroupContent.group.map {
            deleteMessage(it.sourceMessage)
        }.all { it }
    }
}

suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageId: MessageId
) = deleteMessage(chatId, messageId)

suspend fun TelegramBot.delete(
    chat: Chat,
    messageId: MessageId
) = deleteMessage(chat, messageId)

suspend fun TelegramBot.delete(
    message: Message
) = deleteMessage(message)

suspend fun Message.delete(
    requestsExecutor: TelegramBot
) = requestsExecutor.deleteMessage(this)
