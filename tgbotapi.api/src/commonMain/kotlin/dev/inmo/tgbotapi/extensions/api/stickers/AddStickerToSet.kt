package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.stickers.AddStickerToSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    inputSticker: InputSticker
) = execute(
    AddStickerToSet(userId, stickerSetName, inputSticker)
)

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: InputFile,
    emojis: List<String>,
    keywords: List<String> = emptyList()
) = addStickerToSet(
    userId,
    stickerSet.name,
    when (stickerSet.stickerType) {
        StickerType.CustomEmoji -> InputSticker.WithKeywords.CustomEmoji(
            sticker,
            emojis,
            keywords
        )
        StickerType.Mask -> error("Unable to create Mask sticker to the set without maskPosition parameter")
        StickerType.Regular -> InputSticker.WithKeywords.Regular(
            sticker,
            emojis,
            keywords
        )
        is StickerType.Unknown -> error("Unable to create sticker to the set with type ${stickerSet.stickerType}")
    }
)

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: InputFile,
    emojis: List<String>,
    maskPosition: MaskPosition
) = addStickerToSet(
    userId,
    stickerSet.name,
    when (stickerSet.stickerType) {
        StickerType.CustomEmoji -> InputSticker.WithKeywords.CustomEmoji(
            sticker,
            emojis,
            emptyList()
        )
        StickerType.Mask -> InputSticker.Mask(
            sticker,
            emojis,
            maskPosition
        )
        StickerType.Regular -> InputSticker.WithKeywords.Regular(
            sticker,
            emojis,
            emptyList()
        )
        is StickerType.Unknown -> error("Unable to create sticker to the set with type ${stickerSet.stickerType}")
    }
)

suspend fun TelegramBot.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: InputFile,
    emojis: List<String>,
    keywords: List<String> = emptyList()
) = addStickerToSet(
    user.id, stickerSet, sticker, emojis, keywords
)

suspend fun TelegramBot.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: InputFile,
    emojis: List<String>,
    maskPosition: MaskPosition
) = addStickerToSet(
    user.id, stickerSet, sticker, emojis, maskPosition
)
