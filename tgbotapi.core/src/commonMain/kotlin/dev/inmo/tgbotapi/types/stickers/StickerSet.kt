package dev.inmo.tgbotapi.types.stickers

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Serializable
data class SurrogateStickerSet(
    val name: String,
    val title: String,
    val sticker_type: StickerType,
    val is_animated: Boolean? = false,
    val is_video: Boolean? = false,
    val stickers: List<@Serializable(StickerSerializer::class) Sticker> = emptyList(),
    val thumb: PhotoSize? = null
)

@Serializable(StickerSet.Serializer::class)
sealed interface StickerSet {
    val name: String
    val title: String
    val stickerType: StickerType
    val stickers: List<Sticker>
    val isAnimated: Boolean
        get() = false
    val isVideo: Boolean
        get() = false
    val thumb: PhotoSize?
    @Deprecated("Will be removed soon due to its redundancy")
    val containsMasks: Boolean
        get() = this is MaskStickerSet

    object Serializer : KSerializer<StickerSet> {
        override val descriptor: SerialDescriptor = JsonElement.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerSet {
            val json = JsonElement.serializer().deserialize(decoder)
            val surrogate = nonstrictJsonFormat.decodeFromJsonElement(SurrogateStickerSet.serializer(), json)

            return when (surrogate.sticker_type) {
                StickerType.CustomEmoji -> when {
                    surrogate.is_animated == true -> CustomEmojiAnimatedStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<CustomEmojiAnimatedSticker>(),
                        surrogate.thumb
                    )
                    surrogate.is_video == true -> CustomEmojiVideoStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<CustomEmojiVideoSticker>(),
                        surrogate.thumb
                    )
                    else -> CustomEmojiSimpleStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<CustomEmojiSimpleSticker>(),
                        surrogate.thumb
                    )
                }
                StickerType.Mask -> when {
                    surrogate.is_animated == true -> MaskAnimatedStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<MaskAnimatedSticker>(),
                        surrogate.thumb
                    )
                    surrogate.is_video == true -> MaskVideoStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<MaskVideoSticker>(),
                        surrogate.thumb
                    )
                    else -> MaskSimpleStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<MaskSimpleSticker>(),
                        surrogate.thumb
                    )
                }
                StickerType.Regular -> when {
                    surrogate.is_animated == true -> RegularAnimatedStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<RegularAnimatedSticker>(),
                        surrogate.thumb
                    )
                    surrogate.is_video == true -> RegularVideoStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<RegularVideoSticker>(),
                        surrogate.thumb
                    )
                    else -> RegularSimpleStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<RegularSimpleSticker>(),
                        surrogate.thumb
                    )
                }
                is StickerType.Unknown -> UnknownStickerSet(
                    surrogate.name,
                    surrogate.title,
                    surrogate.stickers.filterIsInstance<RegularSimpleSticker>(),
                    surrogate.sticker_type,
                    surrogate.thumb,
                    json
                )
            }
        }

        override fun serialize(encoder: Encoder, value: StickerSet) {
            TODO("Not yet implemented")
        }
    }
}

@Serializable
sealed interface AnimatedStickerSet : StickerSet {
    override val isAnimated: Boolean
        get() = true
}
@Serializable
sealed interface VideoStickerSet : StickerSet {
    override val isVideo: Boolean
        get() = true
}
@Serializable
sealed interface RegularStickerSet : StickerSet
@Serializable
sealed interface MaskStickerSet : StickerSet
@Serializable
sealed interface CustomEmojiStickerSet : StickerSet

@Serializable
data class RegularSimpleStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<RegularSimpleSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : RegularStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Regular
}

@Serializable
data class RegularAnimatedStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<RegularAnimatedSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : RegularStickerSet, AnimatedStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Regular
}

@Serializable
data class RegularVideoStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<RegularVideoSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : RegularStickerSet, VideoStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Regular
}

@Serializable
data class MaskSimpleStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<MaskSimpleSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : MaskStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Mask
}

@Serializable
data class MaskAnimatedStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<MaskAnimatedSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : MaskStickerSet, AnimatedStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Mask
}

@Serializable
data class MaskVideoStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<MaskVideoSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : MaskStickerSet, VideoStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Mask
}

@Serializable
data class CustomEmojiSimpleStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<CustomEmojiSimpleSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : CustomEmojiStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.CustomEmoji
}

@Serializable
data class CustomEmojiAnimatedStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<CustomEmojiAnimatedSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : CustomEmojiStickerSet, AnimatedStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.CustomEmoji
}

@Serializable
data class CustomEmojiVideoStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<CustomEmojiVideoSticker>,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null
) : CustomEmojiStickerSet, VideoStickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.CustomEmoji
}

@Serializable
data class UnknownStickerSet(
    @SerialName(nameField)
    override val name: String,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<Sticker>,
    @SerialName(stickerTypeField)
    override val stickerType: StickerType,
    @SerialName(thumbnailField)
    override val thumb: PhotoSize? = null,
    val raw: JsonElement
) : CustomEmojiStickerSet, VideoStickerSet
