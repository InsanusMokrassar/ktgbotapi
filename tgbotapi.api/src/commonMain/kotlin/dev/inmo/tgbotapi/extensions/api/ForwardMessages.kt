package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.copyMessages
import dev.inmo.tgbotapi.requests.ForwardMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlin.jvm.JvmName

suspend fun TelegramBot.forwardMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: List<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = messageIds.chunked(forwardMessagesLimit.last).flatMap {
    execute(
        ForwardMessages(
            toChatId = toChatId,
            fromChatId = fromChatId,
            messageIds = it,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            removeCaption = removeCaption
        )
    )
}

suspend fun TelegramBot.forwardMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds.toList(),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.forwardMessages(
    toChatId: ChatIdentifier,
    messagesMetas: List<Message.MetaInfo>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = messagesMetas.groupBy { it.chatId }.flatMap { (chatId, messages) ->
    forwardMessages(
        toChatId = toChatId,
        fromChatId = chatId,
        messageIds = messages.map { it.messageId },
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption
    )
}

@JvmName("forwardMessagesWithMessages")
suspend fun TelegramBot.forwardMessages(
    toChatId: ChatIdentifier,
    messages: List<AccessibleMessage>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    messagesMetas = messages.map { it.metaInfo },
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.forward(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: List<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.forward(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.forward(
    toChatId: ChatIdentifier,
    messagesMetas: List<Message.MetaInfo>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    messagesMetas = messagesMetas,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

@JvmName("forwardWithMessages")
suspend fun TelegramBot.forward(
    toChatId: ChatIdentifier,
    messages: List<AccessibleMessage>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = forwardMessages(
    toChatId = toChatId,
    messages = messages,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)
