package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.message.*
import kotlinx.serialization.*

internal const val photoTelegramPaidMediaType = "photo"

@Serializable
data class TelegramPaidMediaPhoto(
    override val file: InputFile,
) : VisualTelegramPaidMedia {
    override val type: String = photoTelegramPaidMediaType

    @SerialName(mediaField)
    override val media: String

    init {
        media = file.fileIdToSend
    } // crutch until js compiling will be fixed
}

fun PhotoSize.toTelegramPaidMediaPhoto(): TelegramPaidMediaPhoto = TelegramPaidMediaPhoto(
    file = fileId,
)
