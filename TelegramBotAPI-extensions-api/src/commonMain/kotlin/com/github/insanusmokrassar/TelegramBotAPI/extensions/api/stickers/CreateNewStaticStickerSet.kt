package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.CreateNewStaticStickerSet
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition

suspend fun RequestsExecutor.createNewStaticStickerSet(
    userId: UserId,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStaticStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)

suspend fun RequestsExecutor.createNewStaticStickerSet(
    userId: UserId,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = execute(
    CreateNewStaticStickerSet(userId, name, sticker, emojis, containsMasks, maskPosition)
)


suspend fun RequestsExecutor.createNewStaticStickerSet(
    user: CommonUser,
    name: String,
    sticker: FileId,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStaticStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)

suspend fun RequestsExecutor.createNewStaticStickerSet(
    user: CommonUser,
    name: String,
    sticker: MultipartFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
) = createNewStaticStickerSet(
    user.id, name, sticker, emojis, containsMasks, maskPosition
)
