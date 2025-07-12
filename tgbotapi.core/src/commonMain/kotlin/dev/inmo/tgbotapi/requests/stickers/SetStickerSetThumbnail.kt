@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun SetStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    format: StickerFormat,
    thumbnail: MultipartFile
): Request<Boolean> {
    return CommonMultipartFileRequest(
        SetStickerSetThumbnail(userId, stickerSetName, format),
        mapOf(thumbnailField to thumbnail)
    )
}

@Suppress("unused")
fun SetStickerSetThumbnail(
    userId: UserId,
    stickerSetName: String,
    format: StickerFormat,
    thumbnail: MultipartFile
): Request<Boolean> = SetStickerSetThumbnail(
    userId = userId,
    stickerSetName = StickerSetName(stickerSetName, ),
    format = format,
    thumbnail = thumbnail
)

@Serializable
data class SetStickerSetThumbnail (
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(formatField)
    val format: StickerFormat,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(thumbnailField)
    val thumbnail: FileId? = null
) : OwnerStickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerSetThumbnail"
}
