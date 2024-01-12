package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlin.jvm.JvmName

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
    messagesMetas: List<Message.MetaInfo>
) = messagesMetas.groupBy { it.chatId }.map { (chatId, messages) ->
    deleteMessages(
        chatId = chatId,
        messageIds = messages.map { it.messageId }
    )
}.all { it }

@JvmName("deleteMessagesWithMessages")
suspend fun TelegramBot.deleteMessages(
    messages: List<AccessibleMessage>
) = deleteMessages(messages.map { it.metaInfo })

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
    messagesMetas: List<Message.MetaInfo>
) = deleteMessages(messagesMetas)

@JvmName("deleteWithMessages")
suspend fun TelegramBot.delete(
    messages: List<AccessibleMessage>
) = deleteMessages(messages)
