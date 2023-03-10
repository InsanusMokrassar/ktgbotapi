package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun SetStickerSetThumbnail(
    userId: UserId,
    stickerSetName: String,
    thumbnail: MultipartFile
): Request<Boolean> {
    return CommonMultipartFileRequest(
        SetStickerSetThumbnail(userId, stickerSetName),
        mapOf(thumbnailField to thumbnail)
    )
}

@Deprecated("Renamed", ReplaceWith("SetStickerSetThumbnail(userId, stickerSetName, thumbnail)", "dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumbnail"))
fun SetStickerSetThumb(
    userId: UserId,
    stickerSetName: String,
    thumbnail: MultipartFile
): Request<Boolean> = SetStickerSetThumbnail(userId, stickerSetName, thumbnail)

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

@Deprecated("Renamed", ReplaceWith("SetStickerSetThumbnail(userId, name, thumbnail)", "dev.inmo.tgbotapi.requests.stickers.SetStickerSetThumbnail"))
fun SetStickerSetThumb(
    userId: UserId,
    name: StickerSetName,
    thumbnail: FileId? = null
) = SetStickerSetThumbnail(userId, name, thumbnail)
