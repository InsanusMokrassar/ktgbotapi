// THIS CODE HAVE BEEN GENERATED AUTOMATICALLY
// TO REGENERATE IT JUST DELETE FILE
// ORIGINAL FILE: ReadBusinessMessage.kt
package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.toChatId
import kotlin.Boolean

public suspend fun TelegramBot.readBusinessMessage(
    businessConnectionId: BusinessConnectionId,
    chat: Chat,
    messageId: MessageId,
): Boolean = readBusinessMessage(
    businessConnectionId = businessConnectionId,
    chatId = with(chat) { chat.id.toChatId() },
    messageId = messageId,
)
