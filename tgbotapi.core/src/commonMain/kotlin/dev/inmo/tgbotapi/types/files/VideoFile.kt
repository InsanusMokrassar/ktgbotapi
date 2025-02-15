package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.TelegramPaidMediaVideo
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(coverField)
    override val cover: Photo? = null,
    @SerialName(startTimestampField)
    override val startTimestamp: Seconds? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, CustomNamedMediaFile, MimedMediaFile, CoveredMediaFile, CustomStartMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile,
    MediaContentVariant, UsefulAsPaidMediaFile

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toTelegramMediaVideo(
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    startTimestamp: Seconds? = this.startTimestamp
) = TelegramMediaVideo(
    file = fileId,
    text = text,
    parseMode = parseMode,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia,
    cover = cover ?.fileId,
    startTimestamp = startTimestamp,
    width = width,
    height = height,
    duration = duration,
    thumb = thumbnail ?.fileId
)

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toTelegramMediaVideo(
    textSources: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false,
    startTimestamp: Seconds? = this.startTimestamp
) = TelegramMediaVideo(
    file = fileId,
    entities = textSources,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia,
    cover = cover ?.fileId,
    startTimestamp = startTimestamp,
    width = width,
    height = height,
    duration = duration,
    thumb = thumbnail ?.fileId
)
@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toTelegramPaidMediaVideo() = TelegramPaidMediaVideo(
    file = fileId,
    width = width,
    height = height,
    duration = duration,
    cover = cover ?.fileId,
    startTimestamp = startTimestamp,
    thumb = thumbnail ?.fileId
)
