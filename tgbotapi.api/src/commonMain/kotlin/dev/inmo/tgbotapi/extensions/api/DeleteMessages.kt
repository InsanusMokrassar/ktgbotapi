package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

suspend fun TelegramBot.deleteMessages(
    chatId: ChatIdentifier,
    messageIds: List<MessageId>
) = messageIds.chunked(deleteMessagesLimit.last).map {
    execute(
        DeleteMessages(
            chatId = chatId,
            messageIds = it
        )
    )
}.all { it }

suspend fun TelegramBot.deleteMessages(
    chatId: ChatIdentifier,
    messageIds: Array<MessageId>
) = deleteMessages(
    chatId = chatId,
    messageIds = messageIds.toList()
)

suspend fun TelegramBot.deleteMessages(
    chatId: ChatIdentifier,
    firstMessageId: MessageId,
    vararg messageIds: MessageId
) = deleteMessages(
    chatId = chatId,
    messageIds = (listOf(firstMessageId) + messageIds.toList())
)

suspend fun TelegramBot.deleteMessages(
    messages: List<AccessibleMessage>
) = messages.groupBy { it.chat }.map { (chat, messages) ->
    deleteMessages(
        chatId = chat.id,
        messageIds = messages.map { it.messageId }
    )
}.all { it }

suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageIds: List<MessageId>
) = deleteMessages(chatId = chatId, messageIds = messageIds)

suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageIds: Array<MessageId>
) = deleteMessages(chatId = chatId, messageIds = messageIds)

suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    firstMessageId: MessageId,
    secondMessageId: MessageId,
    vararg messageIds: MessageId
) = deleteMessages(chatId = chatId, messageIds = (listOf(firstMessageId, secondMessageId) + messageIds.toList()))

suspend fun TelegramBot.delete(
    messages: List<AccessibleMessage>
) = deleteMessages(messages)
