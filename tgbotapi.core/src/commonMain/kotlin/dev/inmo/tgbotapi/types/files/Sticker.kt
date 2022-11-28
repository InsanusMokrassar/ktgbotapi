package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
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
    val file_unique_id: FileUniqueId,
    val type: StickerType,
    val width: Int,
    val height: Int,
    val is_animated: Boolean? = null,
    val is_video: Boolean? = null,
    val thumb: PhotoSize? = null,
    val emoji: String? = null,
    val set_name: StickerSetName? = null,
    val premium_animation: File? = null,
    val mask_position: MaskPosition? = null,
    val custom_emoji_id: CustomEmojiId? = null,
    val file_size: Long? = null
)

// TODO:: Serializer
@Serializable(StickerSerializer::class)
sealed interface Sticker : TelegramMediaFile, SizedMediaFile, ThumbedMediaFile {
    val emoji: String?
    val stickerSetName: StickerSetName?

    val isAnimated
        get() = false
    val isVideo
        get() = false
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
                    surrogate.thumb,
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
                    surrogate.thumb,
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
                    surrogate.thumb,
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
                    surrogate.mask_position ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                surrogate.is_video == true -> MaskVideoSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.mask_position ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                else -> MaskSimpleSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.mask_position ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
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
                    surrogate.custom_emoji_id ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                surrogate.is_video == true -> CustomEmojiVideoSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.custom_emoji_id ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
                else -> CustomEmojiSimpleSticker(
                    surrogate.file_id,
                    surrogate.file_unique_id,
                    surrogate.width,
                    surrogate.height,
                    surrogate.custom_emoji_id ?: error("For mask stickers field mask_position should be presented"),
                    surrogate.thumb,
                    surrogate.emoji,
                    surrogate.set_name,
                    surrogate.file_size
                )
            }
            is StickerType.Unknown -> UnknownSticker(
                surrogate.file_id,
                surrogate.file_unique_id,
                surrogate.width,
                surrogate.height,
                surrogate.thumb,
                surrogate.emoji,
                surrogate.set_name,
                surrogate.file_size,
                json
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Sticker) {
        TODO("Not yet implemented")
    }

}

@Serializable
sealed interface VideoSticker : Sticker {
    override val isVideo: Boolean
        get() = true
}
@Serializable
sealed interface AnimatedSticker : Sticker {
    override val isAnimated: Boolean
        get() = true
}

@Serializable
sealed interface RegularSticker : Sticker {
    val premiumAnimationFile: File?
}

@Serializable
data class RegularSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File?,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker
@Deprecated("Renamed", ReplaceWith("SimpleRegularSticker", "dev.inmo.tgbotapi.types.files.SimpleRegularSticker"))
typealias SimpleSticker = RegularSimpleSticker
@Serializable
data class RegularAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File?,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker, AnimatedSticker
@Serializable
data class RegularVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(premiumAnimationField)
    override val premiumAnimationFile: File?,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : RegularSticker, VideoSticker


@Serializable
sealed interface MaskSticker : Sticker {
    val maskPosition: MaskPosition
}
@Serializable
data class MaskSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker
@Serializable
data class MaskAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker, AnimatedSticker
@Serializable
data class MaskVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : MaskSticker, VideoSticker

@Serializable
sealed interface CustomEmojiSticker : Sticker {
    val customEmojiId: CustomEmojiId
}

@Serializable
data class CustomEmojiSimpleSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : CustomEmojiSticker
@Serializable
data class CustomEmojiAnimatedSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : CustomEmojiSticker, AnimatedSticker
@Serializable
data class CustomEmojiVideoSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(customEmojiIdField)
    override val customEmojiId: CustomEmojiId,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : CustomEmojiSticker, VideoSticker

@Serializable
data class UnknownSticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    override val emoji: String? = null,
    @SerialName(stickerSetNameField)
    override val stickerSetName: StickerSetName? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
    val raw: JsonElement
) : Sticker
