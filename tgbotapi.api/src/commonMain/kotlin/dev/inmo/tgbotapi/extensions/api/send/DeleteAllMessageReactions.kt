package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.DeleteAllActorChatMessageReactions
import dev.inmo.tgbotapi.requests.send.DeleteAllMessageReactions
import dev.inmo.tgbotapi.requests.send.DeleteAllUserMessageReactions
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.Chat

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteAllUserMessageReactions or deleteAllActorChatMessageReactions"
)
public suspend fun TelegramBot.deleteAllMessageReactions(
    chatId: ChatIdentifier,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = execute(
    DeleteAllMessageReactions(chatId, userId, actorChatId)
)

@Warning(
    "Pass either userId or actorChatId, but not both. Prefer deleteAllUserMessageReactions or deleteAllActorChatMessageReactions"
)
public suspend fun TelegramBot.deleteAllMessageReactions(
    chat: Chat,
    userId: UserId? = null,
    actorChatId: ChatId? = null
): Unit = deleteAllMessageReactions(chat.id, userId, actorChatId)

public suspend fun TelegramBot.deleteAllUserMessageReactions(
    chatId: ChatIdentifier,
    userId: UserId
): Unit = execute(
    DeleteAllUserMessageReactions(chatId, userId)
)

public suspend fun TelegramBot.deleteAllUserMessageReactions(
    chat: Chat,
    userId: UserId
): Unit = deleteAllUserMessageReactions(chat.id, userId)

public suspend fun TelegramBot.deleteAllActorChatMessageReactions(
    chatId: ChatIdentifier,
    actorChatId: ChatId
): Unit = execute(
    DeleteAllActorChatMessageReactions(chatId, actorChatId)
)

public suspend fun TelegramBot.deleteAllActorChatMessageReactions(
    chat: Chat,
    actorChatId: ChatId
): Unit = deleteAllActorChatMessageReactions(chat.id, actorChatId)
