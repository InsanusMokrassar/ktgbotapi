package dev.inmo.tgbotapi.extensions.api.verifications

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.verifications.VerifyUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

/** Verifies the target using an optional formatted verification description. */
public suspend fun TelegramBot.verifyUser(
    userId: UserId,
    description: String? = null,
    customDescriptionParseMode: ParseMode? = null,
    customDescriptionEntities: RawMessageEntities? = null
): Unit = execute(VerifyUser(userId, description, customDescriptionParseMode, customDescriptionEntities))

public suspend fun TelegramBot.verifyUser(userId: UserId, description: TextSourcesList): Unit =
    execute(VerifyUser(userId, description))

public suspend fun TelegramBot.verifyUser(
    chat: Chat,
    description: String? = null,
    customDescriptionParseMode: ParseMode? = null,
    customDescriptionEntities: RawMessageEntities? = null
): Unit = verifyUser(chat.id.toChatId(), description, customDescriptionParseMode, customDescriptionEntities)

public suspend fun TelegramBot.verifyUser(chat: Chat, description: TextSourcesList): Unit =
    verifyUser(chat.id.toChatId(), description)
