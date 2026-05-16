package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.mediaField
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TelegramMediaSticker(
    override val file: InputFile,
) : TelegramMedia, InputPollOptionMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend }

    companion object {
        const val TYPE = "sticker"
    }
}
