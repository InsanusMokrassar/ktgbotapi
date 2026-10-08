package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.LivePhotoFile
import dev.inmo.tgbotapi.types.files.toTelegramPaidMediaLivePhoto
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import kotlinx.serialization.*

@Serializable
data class TelegramPaidMediaLivePhoto(
    override val file: InputFile,
    @SerialName(photoField)
    override val photo: InputFile,
) : VisualTelegramPaidMedia, PhotoedTelegramMedia {
    override val type: String = TYPE

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed

    companion object {
        const val TYPE = "live_photo"
    }
}

fun PaidMedia.LivePhoto.toTelegramPaidMediaLivePhoto(): TelegramPaidMediaLivePhoto = livePhoto.toTelegramPaidMediaLivePhoto()
