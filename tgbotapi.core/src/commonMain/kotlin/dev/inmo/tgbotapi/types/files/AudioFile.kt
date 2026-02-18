package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.abstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioFile(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(performerField)
    override val performer: String? = null,
    @SerialName(titleField)
    override val title: String? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: FileSize? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null
) : TelegramMediaFile, CustomNamedMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, TitledMediaFile,
    Performerable, MediaContentVariant

fun AudioFile.asVoiceFile() = VoiceFile(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    duration = duration,
    mimeType = mimeType,
    fileSize = fileSize
)
