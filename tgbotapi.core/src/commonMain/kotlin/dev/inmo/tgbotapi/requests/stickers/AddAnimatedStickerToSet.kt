package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.StandardStickerSetAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*

fun AddAnimatedStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: InputFile,
    emojis: String,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = AddAnimatedStickerToSet(userId, stickerSetName, emojis, sticker as? FileId, maskPosition)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(tgsStickerField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class AddAnimatedStickerToSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(tgsStickerField)
    val sticker: FileId? = null,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null
) : StandardStickerSetAction {
    init {
        if(emojis.isEmpty()) {
            throw IllegalArgumentException("Emojis must not be empty")
        }
    }

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "addStickerToSet"
}
