package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.*

@Serializable
data class TelegramPaidMediaPhoto (
    override val file: InputFile,
) : VisualTelegramPaidMedia {
    override val type: String = TYPE

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed

    companion object {
        const val TYPE = "photo"
    }
}

fun PhotoSize.toTelegramPaidMediaPhoto(): TelegramPaidMediaPhoto = TelegramPaidMediaPhoto(
    file = fileId,
)
