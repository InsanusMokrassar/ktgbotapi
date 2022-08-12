package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.CreateStickerSetAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StandardStickerSetAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*

fun CreateNewAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = CreateNewAnimatedStickerSet(userId, name, title, emojis, sticker as? FileId, containsMasks, maskPosition)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(tgsStickerField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class CreateNewAnimatedStickerSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(tgsStickerField)
    val sticker: FileId? = null,
    @SerialName(containsMasksField)
    @Deprecated("Will be removed soon due to its redundancy")
    val containsMasks: Boolean? = null,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null
) : CreateStickerSetAction {
    init {
        if(emojis.isEmpty()) {
            throw IllegalArgumentException("Emojis must not be empty")
        }
    }

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "createNewStickerSet"
}
