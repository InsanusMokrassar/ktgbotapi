package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.PollMedia
import dev.inmo.tgbotapi.types.media.TelegramMediaLivePhoto
import dev.inmo.tgbotapi.types.media.TelegramPaidMediaLivePhoto
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LivePhotoFile(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(durationField)
    override val duration: Long,
    @Serializable(PhotoSerializer::class)
    @SerialName(photoField)
    val photo: PhotoFile? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: FileSize? = null
) : TelegramMediaFile,
    PollMedia,
    MimedMediaFile,
    PlayableMediaFile,
    SizedMediaFile,
    MediaContentVariant,
    UsefulAsPaidMediaFile

@Suppress("NOTHING_TO_INLINE")
inline fun LivePhotoFile.toTelegramMediaLivePhoto(
    text: String? = null,
    parseMode: ParseMode? = null,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaLivePhoto(
    file = fileId,
    photo = photo ?.fileId ?: fileId,
    text = text,
    parseMode = parseMode,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)

@Suppress("NOTHING_TO_INLINE")
inline fun LivePhotoFile.toTelegramMediaLivePhoto(
    textSources: TextSourcesList,
    spoilered: Boolean = false,
    showCaptionAboveMedia: Boolean = false
) = TelegramMediaLivePhoto(
    file = fileId,
    photo = photo ?.fileId ?: fileId,
    entities = textSources,
    spoilered = spoilered,
    showCaptionAboveMedia = showCaptionAboveMedia
)

@Suppress("NOTHING_TO_INLINE")
inline fun LivePhotoFile.toTelegramPaidMediaLivePhoto() = TelegramPaidMediaLivePhoto(
    file = fileId,
    photo = photo ?.fileId ?: fileId
)
