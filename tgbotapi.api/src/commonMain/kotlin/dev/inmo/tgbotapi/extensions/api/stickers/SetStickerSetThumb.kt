package dev.inmo.tgbotapi.extensions.api.thumbs

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumbnail
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.StickerSet

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    thumbnail: FileId
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    thumbnail: MultipartFile
) = execute(
    SetStickerSetThumbnail(userId, stickerSetName, thumbnail)
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSetName, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSetName: StickerSetName,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSetName, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    thumbnail: FileId
) = setStickerSetThumbnail(
    userId, stickerSet.name, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    userId: UserId,
    stickerSet: StickerSet,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    userId, stickerSet.name, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    thumbnail: FileId
) = setStickerSetThumbnail(
    user.id, stickerSet.name, thumbnail
)

suspend fun TelegramBot.setStickerSetThumbnail(
    user: CommonUser,
    stickerSet: StickerSet,
    thumbnail: MultipartFile
) = setStickerSetThumbnail(
    user.id, stickerSet.name, thumbnail
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(userId, thumbSetName, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: FileId
) = execute(
    SetStickerSetThumbnail(userId, thumbSetName, thumb)
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(userId, thumbSetName, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSetName: String,
    thumb: MultipartFile
) = execute(
    SetStickerSetThumbnail(userId, thumbSetName, thumb)
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(user, thumbSetName, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(user, thumbSetName, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSetName: String,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSetName, thumb
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(userId, thumbSet, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(userId, thumbSet, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    userId: UserId,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    userId, thumbSet.name, thumb
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(user, thumbSet, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: FileId
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

@Deprecated("Renamed in telegram bot api", ReplaceWith("setStickerSetThumbnail(user, thumbSet, thumb)", "dev.inmo.tgbotapi.extensions.api.thumbs.setStickerSetThumbnail"))
suspend fun TelegramBot.setStickerSetThumb(
    user: CommonUser,
    thumbSet: StickerSet,
    thumb: MultipartFile
) = setStickerSetThumb(
    user.id, thumbSet.name, thumb
)

