package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.CommonAbstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.FileUniqueId
import dev.inmo.tgbotapi.types.fileUniqueIdField
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    override val duration: Long? = null,
    override val performer: String? = null,
    override val title: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    override val thumb: PhotoSize? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, TitledMediaFile, Performerable

fun AudioFile.asVoiceFile() = VoiceFile(fileId, fileUniqueId, duration, mimeType, fileSize)
