package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.CopyMessages
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import kotlin.jvm.JvmName

public suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: List<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = messageIds.chunked(copyMessagesLimit.last).flatMap {
    execute(
        CopyMessages(
            toChatId = toChatId,
            fromChatId = fromChatId,
            messageIds = it,
            threadId = threadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            removeCaption = removeCaption,
        ),
    )
}

public suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds.toList(),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

public suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    messagesMetas: List<Message.MetaInfo>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = messagesMetas.groupBy { it.chatId }.flatMap { (chatId, messages) ->
    copyMessages(
        toChatId = toChatId,
        fromChatId = chatId,
        messageIds = messages.map { it.messageId },
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )
}

@JvmName("copyMessagesWithMessages")
public suspend fun TelegramBot.copyMessages(
    toChatId: ChatIdentifier,
    messages: List<AccessibleMessage>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    messagesMetas = messages.map { it.metaInfo },
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

public suspend fun TelegramBot.copy(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: List<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

public suspend fun TelegramBot.copy(
    toChatId: ChatIdentifier,
    fromChatId: ChatIdentifier,
    messageIds: Array<MessageId>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    fromChatId = fromChatId,
    messageIds = messageIds,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

public suspend fun TelegramBot.copy(
    toChatId: ChatIdentifier,
    messagesMetas: List<Message.MetaInfo>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    messagesMetas = messagesMetas,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)

@JvmName("copyWithMessages")
public suspend fun TelegramBot.copy(
    toChatId: ChatIdentifier,
    messages: List<AccessibleMessage>,
    threadId: MessageThreadId? = toChatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    removeCaption: Boolean = false,
): List<MessageId> = copyMessages(
    toChatId = toChatId,
    messages = messages,
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    removeCaption = removeCaption,
)
