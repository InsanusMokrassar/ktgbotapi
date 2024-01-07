package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.utils.MimeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimationFile(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    override val width: Int,
    override val height: Int,
    override val duration: Long? = null,
    override val thumbnail: PhotoSize? = null,
    @SerialName(fileNameField)
    override val fileName: String? = null,
    @SerialName(mimeTypeField)
    override val mimeType: MimeType? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, MimedMediaFile, ThumbedMediaFile, PlayableMediaFile, CustomNamedMediaFile, SizedMediaFile,
    ReplyInfo.External.ContentVariant
