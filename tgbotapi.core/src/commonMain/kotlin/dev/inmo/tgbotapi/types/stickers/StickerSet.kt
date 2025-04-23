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
    val name: StickerSetName,
    val title: String,
    val sticker_type: StickerType,
    val is_animated: Boolean? = false,
    val is_video: Boolean? = false,
    val stickers: List<
        @Serializable(StickerSerializer::class)
        Sticker,
        > = emptyList(),
    val thumb: PhotoSize? = null,
)

@Serializable(StickerSet.Serializer::class)
sealed interface StickerSet {
    val name: StickerSetName
    val title: String
    val stickerType: StickerType
    val stickers: List<Sticker>
    val thumbnail: PhotoSize?

    object Serializer : KSerializer<StickerSet> {
        override val descriptor: SerialDescriptor = JsonElement.serializer().descriptor

        override fun deserialize(decoder: Decoder): StickerSet {
            val json = JsonElement.serializer().deserialize(decoder)
            val surrogate = nonstrictJsonFormat.decodeFromJsonElement(SurrogateStickerSet.serializer(), json)

            return when (surrogate.sticker_type) {
                StickerType.CustomEmoji ->
                    when {
                        else ->
                            CustomEmojiStickerSet(
                                surrogate.name,
                                surrogate.title,
                                surrogate.stickers.filterIsInstance<CustomEmojiSticker>(),
                                surrogate.thumb,
                            )
                    }
                StickerType.Mask ->
                    when {
                        else ->
                            MaskStickerSet(
                                surrogate.name,
                                surrogate.title,
                                surrogate.stickers.filterIsInstance<MaskSticker>(),
                                surrogate.thumb,
                            )
                    }
                StickerType.Regular ->
                    when {
                        else ->
                            RegularStickerSet(
                                surrogate.name,
                                surrogate.title,
                                surrogate.stickers.filterIsInstance<RegularSticker>(),
                                surrogate.thumb,
                            )
                    }
                is StickerType.Unknown ->
                    UnknownStickerSet(
                        surrogate.name,
                        surrogate.title,
                        surrogate.stickers.filterIsInstance<RegularSticker>(),
                        surrogate.sticker_type,
                        surrogate.thumb,
                        json,
                    )
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: StickerSet,
        ) {
            TODO("Not yet implemented")
        }
    }
}

@Serializable
data class RegularStickerSet(
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<RegularSticker>,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Regular
}

@Serializable
data class MaskStickerSet(
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<MaskSticker>,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.Mask
}

@Serializable
data class CustomEmojiStickerSet(
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<CustomEmojiSticker>,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
) : StickerSet {
    @SerialName(stickerTypeField)
    @EncodeDefault
    override val stickerType: StickerType = StickerType.CustomEmoji
}

@Serializable
data class UnknownStickerSet(
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(titleField)
    override val title: String,
    @SerialName(stickersField)
    override val stickers: List<Sticker>,
    @SerialName(stickerTypeField)
    override val stickerType: StickerType,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    val raw: JsonElement,
) : StickerSet
