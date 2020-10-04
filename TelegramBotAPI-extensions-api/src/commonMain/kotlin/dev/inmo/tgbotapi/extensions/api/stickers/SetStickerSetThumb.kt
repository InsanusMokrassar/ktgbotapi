package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.thumbs

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.SetStickerSetThumb
import com.github.insanusmokrassar.TelegramBotAPI.types.CommonUser
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: FileId
) = execute(
    SetStickerSetThumb(userId, thumbSetName, thumb)
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: MultipartFile
) = execute(
    SetStickerSetThumb(userId, thumbSetName, thumb)
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

