package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.stickers.AddStickerToSet
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.requests.stickers.ReplaceStickerInSet
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSetName: StickerSetName,
    oldSticker: FileId,
    newSticker: InputSticker
) = execute(
    ReplaceStickerInSet(userId, stickerSetName, oldSticker, newSticker)
)

suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSetName: String,
    oldSticker: FileId,
    newSticker: InputSticker
) = replaceStickerInSet(userId, StickerSetName(stickerSetName), oldSticker, newSticker)

suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    newSticker: InputSticker
) = replaceStickerInSet(
    userId,
    stickerSet.name,
    oldSticker,
    newSticker
)

suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    emojis: List<String>,
    keywords: List<String> = emptyList()
) = replaceStickerInSet(
    userId,
    stickerSet,
    oldSticker,
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

suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    emojis: List<String>,
    maskPosition: MaskPosition? = null
) = replaceStickerInSet(
    userId,
    stickerSet.name,
    oldSticker,
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

suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    newSticker: InputSticker
) = replaceStickerInSet(
    user.id,
    stickerSet.name,
    oldSticker,
    newSticker
)

suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    emojis: List<String>,
    keywords: List<String> = emptyList()
) = replaceStickerInSet(
    user.id, stickerSet, oldSticker, sticker, emojis, keywords
)

suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    emojis: List<String>,
    maskPosition: MaskPosition? = null
) = replaceStickerInSet(
    user.id, stickerSet, oldSticker, sticker, emojis, maskPosition
)
