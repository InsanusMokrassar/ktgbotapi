package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.CreateStickerSetAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import kotlinx.serialization.*

/**
 * Will create one of [CreateNewStickerSet] types based on the first element of [stickers]
 *
 * @param needsRepainting Will be used only if you are creating custom emojis sticker pack (by passing [stickers] with
 * type [InputSticker.WithKeywords.CustomEmoji])
 */
fun CreateNewStickerSet(
    userId: UserId,
    name: String,
    title: String,
    stickersFormat: StickerFormat,
    stickers: List<InputSticker>,
    needsRepainting: Boolean? = null
): Request<Boolean> {
    val data  = when(stickers.first()) {
        is InputSticker.Mask -> CreateNewStickerSet.Mask(userId, name, title, stickersFormat, stickers.filterIsInstance<InputSticker.Mask>())
        is InputSticker.WithKeywords.CustomEmoji -> CreateNewStickerSet.CustomEmoji(userId, name, title, stickersFormat, stickers.filterIsInstance<InputSticker.WithKeywords.CustomEmoji>(), needsRepainting)
        is InputSticker.WithKeywords.Regular -> CreateNewStickerSet.Regular(userId, name, title, stickersFormat, stickers.filterIsInstance<InputSticker.WithKeywords.Regular>())
    }
    val multipartParts = stickers.mapNotNull { (it.sticker as? MultipartFile) }
    return if (multipartParts.isNotEmpty()) {
        CommonMultipartFileRequest(
            data,
            multipartParts.associateBy { it.fileId }
        )
    } else {
        data
    }
}

@Serializable
sealed interface CreateNewStickerSet : CreateStickerSetAction {
    val stickerType: StickerType
    val stickers: List<InputSticker>
    val stickersFormat: StickerFormat

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "createNewStickerSet"

    @Serializable
    data class Regular(
        @SerialName(userIdField)
        override val userId: UserId,
        @SerialName(nameField)
        override val name: String,
        @SerialName(titleField)
        override val title: String,
        @SerialName(stickerFormatField)
        override val stickersFormat: StickerFormat,
        @SerialName(stickersField)
        override val stickers: List<InputSticker.WithKeywords.Regular>
    ) : CreateNewStickerSet {
        @SerialName(stickerTypeField)
        override val stickerType: StickerType
            get() = StickerType.Regular
    }
    @Serializable
    data class Mask(
        @SerialName(userIdField)
        override val userId: UserId,
        @SerialName(nameField)
        override val name: String,
        @SerialName(titleField)
        override val title: String,
        @SerialName(stickerFormatField)
        override val stickersFormat: StickerFormat,
        @SerialName(stickersField)
        override val stickers: List<InputSticker.Mask>
    ) : CreateNewStickerSet {
        @SerialName(stickerTypeField)
        override val stickerType: StickerType
            get() = StickerType.Mask
    }
    @Serializable
    data class CustomEmoji(
        @SerialName(userIdField)
        override val userId: UserId,
        @SerialName(nameField)
        override val name: String,
        @SerialName(titleField)
        override val title: String,
        @SerialName(stickerFormatField)
        override val stickersFormat: StickerFormat,
        @SerialName(stickersField)
        override val stickers: List<InputSticker.WithKeywords.CustomEmoji>,
        @SerialName(needsRepaintingField)
        val needsRepainting: Boolean? = null
    ) : CreateNewStickerSet {
        @SerialName(stickerTypeField)
        override val stickerType: StickerType
            get() = StickerType.CustomEmoji
    }
}
