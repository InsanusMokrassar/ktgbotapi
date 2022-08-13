package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.CreateStickerSetAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*

internal fun CreateNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    emojis: String,
    stickerType: StickerType = StickerType.Regular,
    pngSticker: InputFile? = null,
    tgsSticker: InputFile? = null,
    webmSticker: InputFile? = null,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = CreateNewStickerSet(
        userId,
        name,
        title,
        emojis,
        stickerType,
        pngSticker as? FileId,
        tgsSticker as? FileId,
        webmSticker as? FileId,
        maskPosition
    )
    return if (pngSticker is MultipartFile || tgsSticker is MultipartFile || webmSticker is MultipartFile) {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(
                (pngSticker as? MultipartFile) ?.let { pngStickerField to it },
                (tgsSticker as? MultipartFile) ?.let { tgsStickerField to it },
                (webmSticker as? MultipartFile) ?.let { webmStickerField to it },
            ).toMap()
        )
    } else {
        data
    }
}

@Serializable
data class CreateNewStickerSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(stickerTypeField)
    val stickerType: StickerType = StickerType.Regular,
    @SerialName(pngStickerField)
    val pngSticker: FileId? = null,
    @SerialName(tgsStickerField)
    val tgsSticker: FileId? = null,
    @SerialName(webmStickerField)
    val webmSticker: FileId? = null,
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
