package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.CreateNewStickerSet
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition

suspend fun RequestsExecutor.createNewStickerSet(
    userId: UserId,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)

suspend fun RequestsExecutor.createNewStickerSet(
    userId: UserId,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)


suspend fun RequestsExecutor.createNewStickerSet(
    user: CommonUser,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)

suspend fun RequestsExecutor.createNewStickerSet(
    user: CommonUser,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)
