package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.requests.stickers.ReplaceStickerInSet
import dev.inmo.tgbotapi.types.StickerFormat
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.types.stickers.StickerSet

public suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSetName: StickerSetName,
    oldSticker: FileId,
    newSticker: InputSticker,
): Boolean =
    execute(
        ReplaceStickerInSet(userId, stickerSetName, oldSticker, newSticker),
    )

public suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSetName: String,
    oldSticker: FileId,
    newSticker: InputSticker,
): Boolean = replaceStickerInSet(userId, StickerSetName(stickerSetName), oldSticker, newSticker)

public suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    newSticker: InputSticker,
): Boolean =
    replaceStickerInSet(
        userId,
        stickerSet.name,
        oldSticker,
        newSticker,
    )

public suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    format: StickerFormat,
    emojis: List<String>,
    keywords: List<String> = emptyList(),
): Boolean =
    replaceStickerInSet(
        userId,
        stickerSet,
        oldSticker,
        when (stickerSet.stickerType) {
            StickerType.CustomEmoji ->
                InputSticker.WithKeywords.CustomEmoji(
                    sticker,
                    format,
                    emojis,
                    keywords,
                )
            StickerType.Mask ->
                InputSticker.Mask(
                    sticker,
                    format,
                    emojis,
                )
            StickerType.Regular ->
                InputSticker.WithKeywords.Regular(
                    sticker,
                    format,
                    emojis,
                    keywords,
                )
            is StickerType.Unknown -> error("Unable to create sticker to the set with type ${stickerSet.stickerType}")
        },
    )

public suspend fun TelegramBot.replaceStickerInSet(
    userId: UserId,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    format: StickerFormat,
    emojis: List<String>,
    maskPosition: MaskPosition? = null,
): Boolean =
    replaceStickerInSet(
        userId,
        stickerSet.name,
        oldSticker,
        when (stickerSet.stickerType) {
            StickerType.CustomEmoji ->
                InputSticker.WithKeywords.CustomEmoji(
                    sticker,
                    format,
                    emojis,
                    emptyList(),
                )
            StickerType.Mask ->
                InputSticker.Mask(
                    sticker,
                    format,
                    emojis,
                    maskPosition,
                )
            StickerType.Regular ->
                InputSticker.WithKeywords.Regular(
                    sticker,
                    format,
                    emojis,
                    emptyList(),
                )
            is StickerType.Unknown -> error("Unable to create sticker to the set with type ${stickerSet.stickerType}")
        },
    )

public suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    newSticker: InputSticker,
): Boolean =
    replaceStickerInSet(
        user.id,
        stickerSet.name,
        oldSticker,
        newSticker,
    )

public suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    format: StickerFormat,
    emojis: List<String>,
    keywords: List<String> = emptyList(),
): Boolean =
    replaceStickerInSet(
        user.id,
        stickerSet,
        oldSticker,
        sticker,
        format,
        emojis,
        keywords,
    )

public suspend fun TelegramBot.replaceStickerInSet(
    user: CommonUser,
    stickerSet: StickerSet,
    oldSticker: FileId,
    sticker: InputFile,
    format: StickerFormat,
    emojis: List<String>,
    maskPosition: MaskPosition? = null,
): Boolean =
    replaceStickerInSet(
        user.id,
        stickerSet,
        oldSticker,
        sticker,
        format,
        emojis,
        maskPosition,
    )
