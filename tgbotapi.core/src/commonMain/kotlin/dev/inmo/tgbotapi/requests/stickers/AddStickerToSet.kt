package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.StandardStickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

fun AddStickerToSet(
    userId: UserId,
    stickerSetName: String,
    inputSticker: InputSticker
): Request<Boolean> {
    val data = AddStickerToSetData(userId, stickerSetName, inputSticker)
    return when (val sticker = inputSticker.sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(sticker.fileId to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class AddStickerToSetData internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(stickerField)
    override val inputSticker: InputSticker
) : StandardStickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "addStickerToSet"
}
