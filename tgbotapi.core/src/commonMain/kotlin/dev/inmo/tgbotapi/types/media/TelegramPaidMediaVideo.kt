package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.fileIdToSend
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.message.payments.PaidMedia
import kotlinx.serialization.*

@Serializable
data class TelegramPaidMediaVideo (
    override val file: InputFile,
    override val thumb: InputFile? = null,
    override val width: Int? = null,
    override val height: Int? = null,
    override val duration: Long? = null,
    @SerialName(supportsStreamingField)
    override val supportsStreaming: Boolean = false,
    @SerialName(coverField)
    override val cover: InputFile? = null,
    @SerialName(startTimestampField)
    override val startTimestamp: Seconds? = null,
) : VisualTelegramPaidMedia,
    SizedTelegramMedia,
    DuratedTelegramMedia,
    ThumbedTelegramMedia,
    CoveredTelegramMedia,
    OptionallyStreamable,
    WithCustomStartTelegramMedia {
    override val type: String = TYPE

    @SerialName(mediaField)
    override val media: String
    init { media = file.fileIdToSend } // crutch until js compiling will be fixed

    companion object {
        const val TYPE = "video"
    }
}

fun VideoFile.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = TelegramPaidMediaVideo(
    file = fileId,
    thumb = thumbnail ?.fileId,
    width = width,
    height = height,
    duration = duration,
    cover = cover ?.fileId,
    startTimestamp = startTimestamp,
)

fun PaidMedia.Video.toTelegramPaidMediaVideo(): TelegramPaidMediaVideo = video.toTelegramPaidMediaVideo()
