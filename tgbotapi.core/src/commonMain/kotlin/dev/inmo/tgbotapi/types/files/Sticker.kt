package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
@RiskFeature("This class is used for serialization/deserialization of Sticker interface")
data class StickerSurrogate(
    val file_id: FileId,
    val file_unique_id: FileUniqueId,
    val width: Int,
    val height: Int,
    val is_animated: Boolean? = null,
    val is_video: Boolean? = null,
    val thumb: PhotoSize? = null,
    val emoji: String? = null,
    val set_name: StickerSetName? = null,
    val mask_position: MaskPosition? = null,
    val file_size: Long? = null
)

// TODO:: Serializer
@Serializable(StickerSerializer::class)
sealed interface Sticker : TelegramMediaFile, SizedMediaFile, ThumbedMediaFile {
    val emoji: String?
    val maskPosition: MaskPosition?
    val stickerSetName: StickerSetName?

    val isAnimated
        get() = this is AnimatedSticker
    val isVideo
        get() = this is VideoSticker

    companion object {
        fun serializer(): KSerializer<Sticker> = StickerSerializer
    }
}

object StickerSerializer : KSerializer<Sticker> {
    override val descriptor: SerialDescriptor = StickerSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): Sticker {
        val surrogate = StickerSurrogate.serializer().deserialize(decoder)

        return when {
            surrogate.is_animated == true -> AnimatedSticker(
                surrogate.file_id,
                surrogate.file_unique_id,
                surrogate.width,
                surrogate.height,
                surrogate.thumb,
                surrogate.emoji,
                surrogate.set_name,
                surrogate.mask_position,
                surrogate.file_size
            )
            surrogate.is_video == true -> VideoSticker(
                surrogate.file_id,
                surrogate.file_unique_id,
                surrogate.width,
                surrogate.height,
                surrogate.thumb,
                surrogate.emoji,
                surrogate.set_name,
                surrogate.mask_position,
                surrogate.file_size
            )
            else -> SimpleSticker(
                surrogate.file_id,
                surrogate.file_unique_id,
                surrogate.width,
                surrogate.height,
                surrogate.thumb,
                surrogate.emoji,
                surrogate.set_name,
                surrogate.mask_position,
                surrogate.file_size
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Sticker) {
        TODO("Not yet implemented")
    }

}

@Serializable
data class SimpleSticker(
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
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : Sticker
@Serializable
data class AnimatedSticker(
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
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : Sticker
@Serializable
data class VideoSticker(
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
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null,
) : Sticker
