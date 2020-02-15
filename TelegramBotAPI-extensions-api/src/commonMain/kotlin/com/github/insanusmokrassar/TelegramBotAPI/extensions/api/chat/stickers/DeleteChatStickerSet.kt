package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.stickers.DeleteChatStickerSet
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat

suspend fun RequestsExecutor.deleteChatStickerSet(
    chatId: ChatIdentifier
) = execute(DeleteChatStickerSet(chatId))

suspend fun RequestsExecutor.deleteChatStickerSet(
    chat: SupergroupChat
) = deleteChatStickerSet(chat.id)
