package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.DeleteActorChatMessageReaction
import dev.inmo.tgbotapi.requests.send.DeleteMessageReaction
import dev.inmo.tgbotapi.requests.send.DeleteUserMessageReaction
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteUserMessageReaction or deleteActorChatMessageReaction"
)
public suspend fun TelegramBot.deleteMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = execute(
    DeleteMessageReaction(chatId, messageId, userId, actorChatId)
)

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteUserMessageReaction or deleteActorChatMessageReaction"
)
public suspend fun TelegramBot.deleteMessageReaction(
    chat: Chat,
    messageId: MessageId,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = deleteMessageReaction(chat.id, messageId, userId, actorChatId)

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteUserMessageReaction or deleteActorChatMessageReaction"
)
public suspend fun TelegramBot.deleteMessageReaction(
    meta: Message.MetaInfo,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = deleteMessageReaction(meta.chatId, meta.messageId, userId, actorChatId)

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteUserMessageReaction or deleteActorChatMessageReaction"
)
public suspend fun TelegramBot.deleteMessageReaction(
    message: AccessibleMessage,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = deleteMessageReaction(message.metaInfo, userId, actorChatId)

public suspend fun TelegramBot.deleteUserMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    userId: UserId
): Unit = execute(
    DeleteUserMessageReaction(chatId, messageId, userId)
)

public suspend fun TelegramBot.deleteUserMessageReaction(
    chat: Chat,
    messageId: MessageId,
    userId: UserId
): Unit = deleteUserMessageReaction(chat.id, messageId, userId)

public suspend fun TelegramBot.deleteUserMessageReaction(
    meta: Message.MetaInfo,
    userId: UserId
): Unit = deleteUserMessageReaction(meta.chatId, meta.messageId, userId)

public suspend fun TelegramBot.deleteUserMessageReaction(
    message: AccessibleMessage,
    userId: UserId
): Unit = deleteUserMessageReaction(message.metaInfo, userId)

public suspend fun TelegramBot.deleteActorChatMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    actorChatId: ChatId
): Unit = execute(
    DeleteActorChatMessageReaction(chatId, messageId, actorChatId)
)

public suspend fun TelegramBot.deleteActorChatMessageReaction(
    chat: Chat,
    messageId: MessageId,
    actorChatId: ChatId
): Unit = deleteActorChatMessageReaction(chat.id, messageId, actorChatId)

public suspend fun TelegramBot.deleteActorChatMessageReaction(
    meta: Message.MetaInfo,
    actorChatId: ChatId
): Unit = deleteActorChatMessageReaction(meta.chatId, meta.messageId, actorChatId)

public suspend fun TelegramBot.deleteActorChatMessageReaction(
    message: AccessibleMessage,
    actorChatId: ChatId
): Unit = deleteActorChatMessageReaction(message.metaInfo, actorChatId)
