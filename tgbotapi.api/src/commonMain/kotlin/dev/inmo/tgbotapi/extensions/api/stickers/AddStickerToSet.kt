package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.stickers.AddStickerToSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSetName: StickerSetName,
    inputSticker: InputSticker
) = execute(
    AddStickerToSet(userId, stickerSetName, inputSticker)
)

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    inputSticker: InputSticker
) = addStickerToSet(userId, StickerSetName(stickerSetName), inputSticker)

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: InputSticker
) = addStickerToSet(
    userId,
    stickerSet.name,
    sticker
)

suspend fun TelegramBot.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: InputFile,
    emojis: List<String>,
    keywords: List<String> = emptyList()
) = addStickerToSet(
    userId,
    stickerSet,
    when (stickerSet.stickerType) {
        StickerType.CustomEmoji -> InputSticker.WithKeywords.CustomEmoji(
            sticker,
            emojis,
            keywords
        )
        StickerType.Mask -> InputSticker.Mask(
            sticker,
            emojis
        )
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
    maskPosition: MaskPosition? = null
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
    sticker: InputSticker
) = addStickerToSet(
    user.id,
    stickerSet.name,
    sticker
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
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSet, sticker, emojis, maskPosition
)
