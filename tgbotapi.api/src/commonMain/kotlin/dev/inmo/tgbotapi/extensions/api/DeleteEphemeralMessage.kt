package dev.inmo.tgbotapi.extensions.api

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.DeleteEphemeralMessage
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.ephemeralMessageId
import dev.inmo.tgbotapi.types.receiverUser
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage

public suspend fun TelegramBot.deleteEphemeralMessage(
    chatId: ChatIdentifier,
    receiverUserId: UserId = requireNotNull(chatId.receiverUser) { "receiverUserId was not provided and chatId ($chatId) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "ephemeralMessageId was not provided and chatId ($chatId) does not carry an ephemeralMessageId" }
): Unit = execute(
    DeleteEphemeralMessage(chatId, receiverUserId, ephemeralMessageId)
)

public suspend fun TelegramBot.deleteEphemeralMessage(
    chat: Chat,
    receiverUserId: UserId = requireNotNull(chat.id.receiverUser) { "receiverUserId was not provided and chat.id (${chat.id}) is not an EphemeralChatId" },
    ephemeralMessageId: EphemeralMessageId = requireNotNull(chat.id.ephemeralMessageId) { "ephemeralMessageId was not provided and chat.id (${chat.id}) does not carry an ephemeralMessageId" }
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
