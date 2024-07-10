package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.media.*

fun PaidMedia.toTelegramPaidMediaOrNull(): TelegramPaidMedia? = when (this) {
    is PaidMedia.Photo -> toTelegramMediaPhoto()
    is PaidMedia.Video -> toTelegramPaidMediaVideo()
    is PaidMedia.Preview, is PaidMedia.Unknown -> null
}

fun PaidMedia.Video.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = this.video.toTelegramPaidMediaVideo()

fun PaidMedia.Photo.toTelegramMediaPhoto(): TelegramPaidMediaPhoto = this.photo.biggest.toTelegramPaidMediaPhoto()
