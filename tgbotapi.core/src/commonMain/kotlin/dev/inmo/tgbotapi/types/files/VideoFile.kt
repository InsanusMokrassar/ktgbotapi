package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
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
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, CustomNamedMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile,
    ReplyInfo.External.ContentVariant

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toTelegramMediaVideo(
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false
) = TelegramMediaVideo(
    fileId,
    text,
    parseMode,
    spoilered,
    width,
    height,
    duration,
    thumbnail ?.fileId
)

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toTelegramMediaVideo(
    textSources: TextSourcesList,
    spoilered: Boolean = false
) = TelegramMediaVideo(
    fileId,
    textSources,
    spoilered,
    width,
    height,
    duration,
    thumbnail ?.fileId
)
