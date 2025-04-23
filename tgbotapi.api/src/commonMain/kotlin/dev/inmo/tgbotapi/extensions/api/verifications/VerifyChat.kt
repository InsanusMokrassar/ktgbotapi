package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.VerifyChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.BusinessChat
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.UnknownChatType
import dev.inmo.tgbotapi.types.chat.UnknownExtendedChat

public suspend fun TelegramBot.verifyChat(
    chatId: ChatIdentifier,
    description: String? = null,
): Boolean = execute(
    VerifyChat(
        chatId = chatId,
        description = description,
    ),
)

/**
 * This method may call [verifyUser] in case when [chat] is [PrivateChat]
 */
public suspend fun TelegramBot.verifyChat(
    chat: Chat,
    description: String? = null,
): Boolean = when (chat) {
    is PrivateChat ->
        verifyUser(
            chat = chat,
            description = description,
        )
    is UnknownExtendedChat,
    is UnknownChatType,
    is BusinessChat,
    is PublicChat,
    ->
        verifyChat(
            chatId = chat.id,
            description = description,
        )
}
