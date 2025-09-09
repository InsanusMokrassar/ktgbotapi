package dev.inmo.tgbotapi.extensions.api.suggested

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.suggested.ApproveSuggestedPost
import dev.inmo.tgbotapi.requests.suggested.DeclineSuggestedPost
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

public suspend fun TelegramBot.declineSuggestedPost(
    chatId: IdChatIdentifier,
    messageId: MessageId,
    comment: String? = null,
): Boolean = execute(
    DeclineSuggestedPost(chatId, messageId, comment)
)

public suspend fun TelegramBot.declineSuggestedPost(
    chat: Chat,
    messageId: MessageId,
    comment: String? = null,
): Boolean = declineSuggestedPost(chat.id, messageId, comment)

public suspend fun TelegramBot.declineSuggestedPost(
    message: Message,
    comment: String? = null,
): Boolean = declineSuggestedPost(message.chat, message.messageId, comment)
