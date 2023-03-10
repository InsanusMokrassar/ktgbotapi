package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun SetStickerSetThumb(
    userId: UserId,
    stickerSetName: String,
    thumb: MultipartFile
): Request<Boolean> {
    return CommonMultipartFileRequest(
        SetStickerSetThumb(userId, stickerSetName),
        mapOf(thumbField to thumb)
    )
}

@Serializable
data class SetStickerSetThumb (
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(thumbField)
    val thumb: FileId? = null
) : OwnerStickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerSetThumb"
}
