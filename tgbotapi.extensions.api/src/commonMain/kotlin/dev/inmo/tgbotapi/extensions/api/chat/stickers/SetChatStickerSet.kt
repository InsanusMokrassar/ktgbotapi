package dev.inmo.tgbotapi.extensions.api.chat.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.stickers.SetChatStickerSet
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat

suspend fun TelegramBot.setChatStickerSet(
    chatId: ChatIdentifier,
    stickerSetName: StickerSetName
) = execute(SetChatStickerSet(chatId, stickerSetName))

suspend fun TelegramBot.setChatStickerSet(
    chat: SupergroupChat,
    stickerSetName: StickerSetName
) = setChatStickerSet(chat.id, stickerSetName)
