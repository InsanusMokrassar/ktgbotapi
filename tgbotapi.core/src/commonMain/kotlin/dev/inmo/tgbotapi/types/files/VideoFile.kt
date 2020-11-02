package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId
import dev.inmo.tgbotapi.types.InputMedia.InputMediaVideo
import dev.inmo.tgbotapi.types.ParseMode.HTMLParseMode
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.fileUniqueIdField
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.utils.MimeType
import dev.inmo.tgbotapi.utils.toHtmlCaptions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    override val width: Int,
    override val height: Int,
    override val duration: Long? = null,
    override val thumb: PhotoSize? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, SizedMediaFile

@Suppress("NOTHING_TO_INLINE")
inline fun VideoFile.toInputMediaVideo(
    caption: String? = null,
    parseMode: ParseMode? = null
) = InputMediaVideo(
    fileId,
    caption,
    parseMode,
    width,
    height,
    duration,
    thumb ?.fileId
)
