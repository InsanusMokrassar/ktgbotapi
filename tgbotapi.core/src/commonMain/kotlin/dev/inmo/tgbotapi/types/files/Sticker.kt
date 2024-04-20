package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.stickers.InputSticker
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Serializable
@RiskFeature("This class is used for serialization/deserialization of Sticker interface")
data class StickerSurrogate(
    val file_id: FileId,
    val file_unique_id: TgFileUniqueId,
    val type: StickerType,
    val width: Int,
    val height: Int,
    val is_animated: Boolean? = null,
    val is_video: Boolean? = null,
    val thumbnail: PhotoSize? = null,
    val emoji: String? = null,
    val set_name: StickerSetName? = null,
    val premium_animation: File? = null,
    val mask_position: MaskPosition? = null,
    val custom_emoji_id: CustomEmojiId? = null,
    val file_size: Long? = null,
    val needs_repainting: Boolean = false,
)

// TODO:: Serializer
@Serializable(StickerSerializer::class)
sealed interface Sticker : TelegramMediaFile, SizedMediaFile, ThumbedMediaFile, MediaContentVariant {
    val emoji: String?
    val stickerSetName: StickerSetName?
    val stickerFormat: StickerFormat

    val isAnimated
        get() = stickerFormat is StickerFormat.Animated
    val isVideo
        get() = stickerFormat is StickerFormat.Video
    val type: StickerType

    fun asInputSticker(emojis: List<String> = emoji ?.let { listOf(it) } ?: error("Unable to create input sticker without emojis")): InputSticker
}

@OptIn(RiskFeature::class)
object StickerSerializer : KSerializer<Sticker> {
    override val descriptor: SerialDescriptor = StickerSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): Sticker {
        val json = JsonElement.serializer().deserialize(decoder)
        val surrogate = nonstrictJsonFormat.decodeFromJsonElement(StickerSurrogate.serializer(), json)

        return when (surrogate.type) {
            StickerType.Regular -> when {
                surrogate.is_animated == true -> RegularAnimatedSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.premium_animation,
                    surrogate.file_size
                )
                surrogate.is_video == true -> RegularVideoSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.premium_animation,
                    surrogate.file_size
                )
                else -> RegularSimpleSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.premium_animation,
                    surrogate.file_size
                )
            }
            StickerType.Mask -> when {
                surrogate.is_animated == true -> MaskAnimatedSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.mask_position,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                surrogate.is_video == true -> MaskVideoSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.mask_position,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                else -> MaskSimpleSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.mask_position,
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
            }
            StickerType.CustomEmoji -> when {
                surrogate.is_animated == true -> CustomEmojiAnimatedSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.custom_emoji_id ?: error("For custom emoji stickers field custom_emoji_id should be presented"),
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size,
                    surrogate.needs_repainting
                )
                surrogate.is_video == true -> CustomEmojiVideoSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.custom_emoji_id ?: error("For custom emoji stickers field custom_emoji_id should be presented"),
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size,
                    surrogate.needs_repainting
                )
                else -> CustomEmojiSimpleSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.custom_emoji_id ?: error("For custom emoji stickers field custom_emoji_id should be presented"),
                    surrogate.thumbnail,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size,
                    surrogate.needs_repainting
                )
            }
            is StickerType.Unknown -> UnknownSticker(
                surrogate.file_id,
                surrogate.file_unique_id,
                surrogate.width,
                surrogate.height,
                surrogate.thumbnail,
                surrogate.emoji,
                surrogate.set_name,
                surrogate.file_size,
                when {
                    surrogate.is_animated == true -> StickerFormat.Animated
                    surrogate.is_video == true -> StickerFormat.Video
                    else -> StickerFormat.Static
                },
                surrogate.type,
                json
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Sticker) {
        with(value) {
           StickerSurrogate.serializer().serialize(
               encoder,
               StickerSurrogate(
                   fileId,
                   fileUniqueId,
                   type,
                   width,
                   height,
                   isAnimated,
                   isVideo,
                   thumbnail,
                   emoji,
                   stickerSetName,
                   (this as? RegularSticker) ?.premiumAnimationFile,
                   (this as? MaskSticker) ?.maskPosition,
                   (this as? CustomEmojiSticker) ?.customEmojiId,
                   fileSize,
                   (this as? CustomEmojiSticker) ?.needsRepainting ?: false
               )
           )
        }
    }

}

@Serializable
sealed interface VideoSticker : Sticker {
    override val isVideo: Boolean
        get() = true

    override val stickerFormat: StickerFormat
        get() = StickerFormat.Video
}
@Serializable
sealed interface AnimatedSticker : Sticker {
    override val isAnimated: Boolean
        get() = true

    override val stickerFormat: StickerFormat
        get() = StickerFormat.Animated
}

@Serializable
sealed interface RegularSticker : Sticker {
    val premiumAnimationFile: File?

    override val type: StickerType.Regular
        get() = StickerType.Regular

    override fun asInputSticker(emojis: List<String>) = InputSticker.WithKeywords.Regular(
        fileId,
        stickerFormat,
        emojis,
        emptyList()
    )
}

@Serializable
data class RegularSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker {
    @SerialName(stickerFormatField)
    @EncodeDefault
    override val stickerFormat: StickerFormat = StickerFormat.Static
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Regular
        get() = StickerType.Regular
}

@Serializable
data class RegularAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker, AnimatedSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Regular
        get() = StickerType.Regular
}
@Serializable
data class RegularVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker, VideoSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Regular
        get() = StickerType.Regular
}


@Serializable
sealed interface MaskSticker : Sticker {
    val maskPosition: MaskPosition?

    override val type: StickerType.Mask
        get() = StickerType.Mask

    override fun asInputSticker(emojis: List<String>) = InputSticker.Mask(
        fileId,
        stickerFormat,
        emojis,
        maskPosition
    )
}
@Serializable
data class MaskSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker {
    @SerialName(stickerFormatField)
    @EncodeDefault
    override val stickerFormat: StickerFormat = StickerFormat.Static

    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Mask
        get() = StickerType.Mask
}
@Serializable
data class MaskAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker, AnimatedSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Mask
        get() = StickerType.Mask
}
@Serializable
data class MaskVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker, VideoSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.Mask
        get() = StickerType.Mask
}

@Serializable
sealed interface CustomEmojiSticker : Sticker {
    val customEmojiId: CustomEmojiId
    val needsRepainting: Boolean

    override val type: StickerType.CustomEmoji
        get() = StickerType.CustomEmoji

    override fun asInputSticker(emojis: List<String>) = InputSticker.WithKeywords.CustomEmoji(
        fileId,
        stickerFormat,
        emojis,
        emptyList()
    )
}

@Serializable
data class CustomEmojiSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    @SerialName(needsRepaintingField)
    override val needsRepainting: Boolean = false
) : CustomEmojiSticker {
    @SerialName(stickerFormatField)
    @EncodeDefault
    override val stickerFormat: StickerFormat = StickerFormat.Static

    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.CustomEmoji
        get() = StickerType.CustomEmoji
}
@Serializable
data class CustomEmojiAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    @SerialName(needsRepaintingField)
    override val needsRepainting: Boolean = false,
) : CustomEmojiSticker, AnimatedSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.CustomEmoji
        get() = StickerType.CustomEmoji
}
@Serializable
data class CustomEmojiVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    @SerialName(needsRepaintingField)
    override val needsRepainting: Boolean = false,
) : CustomEmojiSticker, VideoSticker {
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    @EncodeDefault
    override val type: StickerType.CustomEmoji
        get() = StickerType.CustomEmoji
}

@Serializable
data class UnknownSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: TgFileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbnailField)
    override val thumbnail: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    @SerialName(stickerFormatField)
    override val stickerFormat: StickerFormat = StickerFormat.Static,
    @SerialName(stickerTypeField)
    @Serializable(StickerType.Serializer::class)
    override val type: StickerType = StickerType.Regular,
    val raw: JsonElement
) : Sticker {
    override fun asInputSticker(emojis: List<String>) = InputSticker.WithKeywords.Regular(
        fileId,
        stickerFormat,
        emojis,
        emptyList()
    )
}
