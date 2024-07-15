package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlin.jvm.JvmName

public suspend fun TelegramBot.deleteMessages(
    chatId: ChatIdentifier,
    messageIds: List<MessageId>
): Boolean = messageIds.chunked(deleteMessagesLimit.last).map {
    execute(
        DeleteMessages(
            chatId = chatId,
            messageIds = it
        )
    )
}.all { it }

public suspend fun TelegramBot.deleteMessages(
    chatId: ChatIdentifier,
    messageIds: Array<MessageId>
): Boolean = deleteMessages(
    chatId = chatId,
    messageIds = messageIds.toList()
)

public suspend fun TelegramBot.deleteMessages(
    messagesMetas: List<Message.MetaInfo>
): Boolean = messagesMetas.groupBy { it.chatId }.map { (chatId, messages) ->
    deleteMessages(
        chatId = chatId,
        messageIds = messages.map { it.messageId }
    )
}.all { it }

@JvmName("deleteMessagesWithMessages")
public suspend fun TelegramBot.deleteMessages(
    messages: List<AccessibleMessage>
): Boolean = deleteMessages(messages.map { it.metaInfo })

public suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageIds: List<MessageId>
): Boolean = deleteMessages(chatId = chatId, messageIds = messageIds)

public suspend fun TelegramBot.delete(
    chatId: ChatIdentifier,
    messageIds: Array<MessageId>
): Boolean = deleteMessages(chatId = chatId, messageIds = messageIds)

public suspend fun TelegramBot.delete(
    messagesMetas: List<Message.MetaInfo>
): Boolean = deleteMessages(messagesMetas)

@JvmName("deleteWithMessages")
public suspend fun TelegramBot.delete(
    messages: List<AccessibleMessage>
): Boolean = deleteMessages(messages)
