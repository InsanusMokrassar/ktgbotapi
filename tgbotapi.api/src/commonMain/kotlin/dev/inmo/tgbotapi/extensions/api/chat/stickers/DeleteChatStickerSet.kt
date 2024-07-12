package dev.inmo.tgbotapi.extensions.api.chat.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.stickers.DeleteChatStickerSet
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.SupergroupChat

public suspend fun TelegramBot.deleteChatStickerSet(
    chatId: ChatIdentifier
): Boolean = execute(DeleteChatStickerSet(chatId))

public suspend fun TelegramBot.deleteChatStickerSet(
    chat: SupergroupChat
): Boolean = deleteChatStickerSet(chat.id)
