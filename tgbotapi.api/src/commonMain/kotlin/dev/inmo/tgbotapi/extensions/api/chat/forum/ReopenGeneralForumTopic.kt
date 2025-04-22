package dev.inmo.tgbotapi.extensions.api.chat.forum

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.forum.ReopenGeneralForumTopic
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.Chat

public suspend fun TelegramBot.reopenGeneralForumTopic(chatId: ChatIdentifier): Boolean =
    execute(
        ReopenGeneralForumTopic(chatId),
    )

public suspend fun TelegramBot.reopenGeneralForumTopic(chat: Chat): Boolean = reopenGeneralForumTopic(chat.id)
