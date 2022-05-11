package dev.inmo.tgbotapi.extensions.api.chat.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.stickers.DeleteChatStickerSet
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.SupergroupChat

suspend fun TelegramBot.deleteChatStickerSet(
    chatId: ChatIdentifier
) = execute(DeleteChatStickerSet(chatId))

suspend fun TelegramBot.deleteChatStickerSet(
    chat: SupergroupChat
) = deleteChatStickerSet(chat.id)
