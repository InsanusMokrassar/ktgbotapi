package dev.inmo.tgbotapi.extensions.api.thumbs

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stickers.SetCustomEmojiStickerSetThumbnail
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.stickers.StickerSet

public suspend fun TelegramBot.setCustomEmojiStickerSetThumbnail(
    stickerSetName: StickerSetName,
    customEmojiId: CustomEmojiId,
): Boolean = execute(
    SetCustomEmojiStickerSetThumbnail(stickerSetName, customEmojiId),
)

public suspend fun TelegramBot.setCustomEmojiStickerSetThumbnail(
    stickerSet: StickerSet,
    customEmojiId: CustomEmojiId,
): Boolean = setCustomEmojiStickerSetThumbnail(
    stickerSet.name,
    customEmojiId,
)
