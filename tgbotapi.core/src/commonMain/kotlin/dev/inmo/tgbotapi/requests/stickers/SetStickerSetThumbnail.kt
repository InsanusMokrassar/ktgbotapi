package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun SetStickerSetThumbnail(
    userId: UserId,
    stickerSetName: StickerSetName,
    thumbnail: MultipartFile
): Request<Boolean> {
    return CommonMultipartFileRequest(
        SetStickerSetThumbnail(userId, stickerSetName),
        mapOf(thumbnailField to thumbnail)
    )
}

fun SetStickerSetThumbnail(
    userId: UserId,
    stickerSetName: String,
    thumbnail: MultipartFile
): Request<Boolean> = SetStickerSetThumbnail(
    userId = userId,
    stickerSetName = StickerSetName(stickerSetName),
    thumbnail = thumbnail
)

@Serializable
data class SetStickerSetThumbnail (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(thumbnailField)
    val thumbnail: FileId? = null
) : OwnerStickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerSetThumbnail"
}
