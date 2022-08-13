package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition


fun CreateNewRegularStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Regular,
    pngSticker = sticker
)

fun CreateNewRegularVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Regular,
    webmSticker = sticker
)

fun CreateNewRegularAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Regular,
    tgsSticker = sticker
)


fun CreateNewMaskStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    maskPosition: MaskPosition
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Mask,
    pngSticker = sticker,
    maskPosition = maskPosition
)

fun CreateNewMaskVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    maskPosition: MaskPosition
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Mask,
    webmSticker = sticker,
    maskPosition = maskPosition
)

fun CreateNewMaskAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    maskPosition: MaskPosition
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    StickerType.Mask,
    tgsSticker = sticker,
    maskPosition = maskPosition
)
