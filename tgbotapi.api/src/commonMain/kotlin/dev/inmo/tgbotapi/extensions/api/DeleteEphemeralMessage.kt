package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteEphemeralMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.EphemeralChatId
import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage

public suspend fun TelegramBot.deleteEphemeralMessage(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId
): Unit = execute(
    DeleteEphemeralMessage(chatId, receiverUserId, ephemeralMessageId)
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 */
public suspend fun TelegramBot.deleteEphemeralMessage(
    chatId: EphemeralChatId
): Unit = execute(
    DeleteEphemeralMessage(chatId)
)

public suspend fun TelegramBot.deleteEphemeralMessage(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId
): Unit = deleteEphemeralMessage(chat.id, receiverUserId, ephemeralMessageId)

/**
 * Convenience overload requiring [message] to carry non-null [PossiblyEphemeralMessage.receiverUser] and
 * [PossiblyEphemeralMessage.ephemeralMessageId] (as returned by any of the ephemeral send requests)
 */
public suspend fun TelegramBot.deleteEphemeralMessage(
    message: PossiblyEphemeralMessage
): Unit = deleteEphemeralMessage(
    message.chat.id,
    message.receiverUser ?.id ?: error("Message ${message.messageId} in chat ${message.chat.id} is not ephemeral: receiverUser is null"),
    message.ephemeralMessageId ?: error("Message ${message.messageId} in chat ${message.chat.id} is not ephemeral: ephemeralMessageId is null")
)
