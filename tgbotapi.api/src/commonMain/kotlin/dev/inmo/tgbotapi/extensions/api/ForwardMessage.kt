package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.EffectId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.directMessageThreadId
import dev.inmo.tgbotapi.types.threadId

public suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = execute(
    ForwardMessage(
        fromChatId = fromChatId,
        toChatId = toChatId,
        messageId = messageId,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        startTimestamp = startTimestamp,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId
    )
)

public suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChatId: ChatIdentifier,
    messageId: MessageId,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = forwardMessage(
    fromChatId = fromChat.id,
    toChatId = toChatId,
    messageId = messageId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId
)

public suspend fun TelegramBot.forwardMessage(
    fromChatId: ChatIdentifier,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = forwardMessage(
    fromChatId = fromChatId,
    toChatId = toChat.id,
    messageId = messageId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId
)

public suspend fun TelegramBot.forwardMessage(
    fromChat: Chat,
    toChat: Chat,
    messageId: MessageId,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = forwardMessage(
    fromChatId = fromChat.id,
    toChatId = toChat.id,
    messageId = messageId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId
)

public suspend fun TelegramBot.forwardMessage(
    toChatId: ChatIdentifier,
    message: AccessibleMessage,
    threadId: MessageThreadId? = toChatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChatId.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = forwardMessage(
    fromChat = message.chat,
    toChatId = toChatId,
    messageId = message.messageId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId
)

public suspend fun TelegramBot.forwardMessage(
    toChat: Chat,
    message: AccessibleMessage,
    threadId: MessageThreadId? = toChat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = toChat.id.directMessageThreadId,
    startTimestamp: Seconds? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null
): PossiblyForwardedMessage = forwardMessage(
    fromChat = message.chat,
    toChat = toChat,
    messageId = message.messageId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    startTimestamp = startTimestamp,
    disableNotification = disableNotification,
    protectContent = protectContent,
    effectId = effectId
)
