package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.CreateStickerSetAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StandardStickerSetAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*

fun CreateNewVideoStickerSet(
    userId: UserId,
    linkName: String,
    title: String,
    sticker: InputFile,
    emojis: String,
    containsMasks: Boolean? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = CreateNewVideoStickerSet(userId, linkName, title, emojis, sticker as? FileId, containsMasks, maskPosition)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(webmStickerField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class CreateNewVideoStickerSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(webmStickerField)
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
