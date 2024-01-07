package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.CopyMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage

suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: List<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = messageIds.chunked(copyMessagesLimit.last).flatMap {
    execute(
        CopyMessages(
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

suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = copyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds.toList(),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    firstMessageId: MessageId,
    vararg messageIds: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = copyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = (listOf(firstMessageId) + messageIds.toList()),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption
)

suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    messages: List<AccessibleMessage>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false
) = messages.groupBy { it.chat }.flatMap { (chat, messages) ->
    copyMessages(
        toChatId = toChatId,
        fromChatId = chat.id,
        messageIds = messages.map { it.messageId },
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption
    )
}
