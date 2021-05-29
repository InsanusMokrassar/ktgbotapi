package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InputMedia.InputMediaVideo
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, CustomNamedMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toInputMediaVideo(
    text: String? = null,
    parseMode: ParseMode? = null
) = InputMediaVideo(
    fileId,
    text,
    parseMode,
    width,
    height,
    duration,
    thumb ?.fileId
)

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toInputMediaVideo(
    textSources: TextSourcesList
) = InputMediaVideo(
    fileId,
    textSources,
    width,
    height,
    duration,
    thumb ?.fileId
)
