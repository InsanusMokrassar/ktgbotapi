package dev.inmo.tgbotapi.extensions.api.chat.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.stickers.SetChatStickerSet
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.SupergroupChat

public suspend fun TelegramBot.setChatStickerSet(
    chatId: ChatIdentifier,
    stickerSetName: StickerSetName,
): Boolean = execute(SetChatStickerSet(chatId, stickerSetName))

public suspend fun TelegramBot.setChatStickerSet(
    chat: SupergroupChat,
    stickerSetName: StickerSetName,
): Boolean = setChatStickerSet(chat.id, stickerSetName)
