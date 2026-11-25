package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.VerifyChat
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

/** Verifies the target using an optional formatted verification description. */
public suspend fun TelegramBot.verifyChat(
    chatId: ChatIdentifier,
    description: String? = null,
    customDescriptionParseMode: ParseMode? = null,
    customDescriptionEntities: RawMessageEntities? = null
): Unit = execute(VerifyChat(chatId, description, customDescriptionParseMode, customDescriptionEntities))

public suspend fun TelegramBot.verifyChat(chatId: ChatIdentifier, description: TextSourcesList): Unit =
    execute(VerifyChat(chatId, description))

/** Uses [verifyUser] for private chats. */
public suspend fun TelegramBot.verifyChat(
    chat: Chat,
    description: String? = null,
    customDescriptionParseMode: ParseMode? = null,
    customDescriptionEntities: RawMessageEntities? = null
): Unit = if (chat is PrivateChat) {
    verifyUser(chat, description, customDescriptionParseMode, customDescriptionEntities)
} else {
    verifyChat(chat.id, description, customDescriptionParseMode, customDescriptionEntities)
}

public suspend fun TelegramBot.verifyChat(chat: Chat, description: TextSourcesList): Unit =
    if (chat is PrivateChat) verifyUser(chat, description) else verifyChat(chat.id, description)
