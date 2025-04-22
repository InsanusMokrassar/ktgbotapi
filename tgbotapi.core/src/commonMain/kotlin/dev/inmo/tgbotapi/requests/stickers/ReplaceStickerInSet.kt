package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.StandardStickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun ReplaceStickerInSet(
    userId: UserId,
    stickerSetName: StickerSetName,
    oldSticker: FileId,
    newSticker: InputSticker,
): Request<Boolean> {
    val data =
        ReplaceStickerInSetData(
            userId = userId,
            name = stickerSetName,
            oldSticker = oldSticker,
            newSticker = newSticker,
        )
    return when (val sticker = newSticker.sticker) {
        is MultipartFile ->
            CommonMultipartFileRequest(
                data,
                mapOf(sticker.fileId to sticker),
            )
        is FileId -> data
    }
}

fun ReplaceStickerInSet(
    userId: UserId,
    stickerSetName: String,
    oldSticker: FileId,
    newSticker: InputSticker,
): Request<Boolean> =
    ReplaceStickerInSetData(
        userId = userId,
        name = StickerSetName(stickerSetName),
        oldSticker = oldSticker,
        newSticker = newSticker,
    )

@Serializable
data class ReplaceStickerInSetData internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(oldStickerField)
    val oldSticker: FileId,
    @SerialName(stickerField)
    override val newSticker: InputSticker,
) : StandardStickerSetAction {
    override fun method(): String = "replaceStickerInSet"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
