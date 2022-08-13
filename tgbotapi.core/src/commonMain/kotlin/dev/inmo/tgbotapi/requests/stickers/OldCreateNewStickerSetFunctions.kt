package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.StickerType
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition


fun CreateNewVideoStickerSet(
    userId: UserId,
    linkName: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> = CreateNewStickerSet(
    userId,
    linkName,
    title,
    emojis,
    if (containsMasks == true) StickerType.Mask else StickerType.Regular,
    webmSticker = sticker,
    maskPosition = maskPosition
)

fun CreateNewStaticStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    if (containsMasks == true) StickerType.Mask else StickerType.Regular,
    pngSticker = sticker,
    maskPosition = maskPosition
)

fun CreateNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    if (containsMasks == true) StickerType.Mask else StickerType.Regular,
    pngSticker = sticker,
    maskPosition = maskPosition
)

fun CreateNewAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> = CreateNewStickerSet(
    userId,
    name,
    title,
    emojis,
    if (containsMasks == true) StickerType.Mask else StickerType.Regular,
    tgsSticker = sticker,
    maskPosition = maskPosition
)
