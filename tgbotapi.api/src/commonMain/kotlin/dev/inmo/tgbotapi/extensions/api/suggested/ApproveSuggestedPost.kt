package dev.inmo.tgbotapi.extensions.api.suggested

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.suggested.ApproveSuggestedPost
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

public suspend fun TelegramBot.approveSuggestedPost(
    chatId: IdChatIdentifier,
    messageId: MessageId,
    sendDate: TelegramDate? = null,
): Boolean = execute(
    ApproveSuggestedPost(chatId, messageId, sendDate)
)

public suspend fun TelegramBot.approveSuggestedPost(
    chat: Chat,
    messageId: MessageId,
    sendDate: TelegramDate? = null,
): Boolean = approveSuggestedPost(chat.id, messageId, sendDate)

public suspend fun TelegramBot.approveSuggestedPost(
    message: Message,
    sendDate: TelegramDate? = null,
): Boolean = approveSuggestedPost(message.chat, message.messageId, sendDate)
