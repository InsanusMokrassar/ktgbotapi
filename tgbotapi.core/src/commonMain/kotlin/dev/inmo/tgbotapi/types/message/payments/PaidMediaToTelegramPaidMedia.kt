package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.media.TelegramPaidMedia
import dev.inmo.tgbotapi.types.media.TelegramPaidMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramPaidMediaVideo

fun PaidMedia.toTelegramPaidMediaOrNull(): TelegramPaidMedia? = when (this) {
    is PaidMedia.Photo -> TelegramPaidMediaPhoto(
        file = this.photo.biggest.fileId
    )
    is PaidMedia.Video -> TelegramPaidMediaPhoto(
        file = this.video.fileId
    )
    is PaidMedia.Preview, is PaidMedia.Unknown -> null
}

fun PaidMedia.Video.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = TelegramPaidMediaVideo(
        file = this.video.fileId,
        duration = this.video.duration,
        width = this.video.width,
        height = this.video.height,
        thumb = this.video.thumbnail?.fileId
    )

fun PaidMedia.Photo.toTelegramMediaPhoto(): TelegramPaidMediaPhoto = TelegramPaidMediaPhoto(
    file = this.photo.fileId
)
