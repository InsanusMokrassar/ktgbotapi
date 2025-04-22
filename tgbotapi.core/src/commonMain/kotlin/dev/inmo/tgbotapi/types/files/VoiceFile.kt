package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : TelegramMediaFile, MimedMediaFile, PlayableMediaFile, MediaContentVariant

fun VoiceFile.asAudioFile(
    performer: String? = null,
    title: String? = null,
    fileName: String? = null,
) = AudioFile(fileId, fileUniqueId, duration, performer, title, fileName, mimeType, fileSize)
