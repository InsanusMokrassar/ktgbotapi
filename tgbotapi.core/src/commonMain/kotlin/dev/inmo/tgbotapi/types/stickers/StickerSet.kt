package dev.inmo.tgbotapi.types.stickers

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import kotlinx.serialization.*

@Serializable
sealed interface StickerSet {
    @SerialName(nameField)
    val name: String
    @SerialName(titleField)
    val title: String
    @SerialName(stickerTypeField)
    val stickerType: StickerType
    @SerialName(stickersField)
    val stickers: List<Sticker>
    @SerialName(isAnimatedField)
    val isAnimated: Boolean
    @SerialName(isVideoField)
    val isVideo: Boolean
    @SerialName(containsMasksField)
    @Deprecated("Will be removed soon due to its redundancy")
    val containsMasks: Boolean
        get() = this is MaskStickerSet
    @SerialName(thumbField)
    val thumb: PhotoSize?
}

@Serializable
data class RegularStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<RegularSticker>,
    @SerialName(isAnimatedField)
    override val isAnimated: Boolean = false,
    @SerialName(isVideoField)
    override val isVideo: Boolean = false,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Regular
}

@Serializable
data class MaskStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<MaskSticker>,
    @SerialName(isAnimatedField)
    override val isAnimated: Boolean = false,
    @SerialName(isVideoField)
    override val isVideo: Boolean = false,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Mask
}

@Serializable
data class CustomEmojiStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<CustomEmojiSticker>,
    @SerialName(isAnimatedField)
    override val isAnimated: Boolean = false,
    @SerialName(isVideoField)
    override val isVideo: Boolean = false,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.CustomEmoji
}
