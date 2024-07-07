package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import kotlinx.serialization.*

internal const val videoTelegramPaidMediaType = "video"

@Serializable
data class TelegramPaidMediaVideo (
    override val file: InputFile,
    override val thumb: InputFile? = null,
    override val width: Int? = null,
    override val height: Int? = null,
    override val duration: Long? = null,
    @SerialName(supportsStreamingField)
    val supportsStreaming: Boolean = false,
) : TelegramPaidMedia, SizedTelegramMedia, DuratedTelegramMedia, ThumbedTelegramMedia, VisualMediaGroupMemberTelegramPaidMedia {
    override val type: String = videoTelegramPaidMediaType

    override fun serialize(format: StringFormat): String = format.encodeToString(serializer(), this)

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed
}

fun VideoFile.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = TelegramPaidMediaVideo(
    file = fileId,
    thumb = thumbnail ?.fileId,
    width = width,
    height = height,
    duration = duration
)

fun PaidMedia.Video.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = video.toTelegramPaidMediaVideo()
