package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ClassCastsIncluded
@Serializable(InputStickerSerializer::class)
sealed interface InputSticker {
    val sticker: InputFile
    val format: StickerFormat
    val emojisList: List<String>

    @Serializable
    data class Mask(
        @SerialName(stickerField)
        override val sticker: InputFile,
        @SerialName(formatField)
        override val format: StickerFormat,
        @SerialName(emojiListField)
        override val emojisList: List<String>,
        @SerialName(maskPositionField)
        val maskPosition: MaskPosition? = null
    ) : InputSticker

    @Serializable
    sealed interface WithKeywords : InputSticker {
        val keywords: List<String>

        @Serializable
        data class Regular(
            @SerialName(stickerField)
            override val sticker: InputFile,
            @SerialName(formatField)
            override val format: StickerFormat,
            @SerialName(emojiListField)
            override val emojisList: List<String>,
            @SerialName(keywordsField)
            override val keywords: List<String>
        ) : WithKeywords

        @Serializable
        data class CustomEmoji(
            @SerialName(stickerField)
            override val sticker: InputFile,
            @SerialName(formatField)
            override val format: StickerFormat,
            @SerialName(emojiListField)
            override val emojisList: List<String>,
            @SerialName(keywordsField)
            override val keywords: List<String>
        ) : WithKeywords
    }
}

object InputStickerSerializer : KSerializer<InputSticker>, MapperSerializer<InputStickerSerializer.SurrogateInputSticker, InputSticker>(
    SurrogateInputSticker.serializer(),
    { it ->
        when (it) {
            is InputSticker.Mask -> SurrogateInputSticker(
                it.sticker,
                it.format,
                it.emojisList,
                emptyList(),
                it.maskPosition,
                StickerType.Mask
            )
            is InputSticker.WithKeywords.CustomEmoji -> SurrogateInputSticker(
                it.sticker,
                it.format,
                it.emojisList,
                it.keywords,
                null,
                StickerType.CustomEmoji
            )
            is InputSticker.WithKeywords.Regular -> SurrogateInputSticker(
                it.sticker,
                it.format,
                it.emojisList,
                it.keywords,
                null,
                StickerType.Regular
            )
        }
    },
    { it ->
        when (it.internalType) {
            StickerType.CustomEmoji -> InputSticker.WithKeywords.CustomEmoji(
                it.sticker,
                it.format,
                it.emojisList,
                it.keywords
            )
            StickerType.Mask -> InputSticker.Mask(
                it.sticker,
                it.format,
                it.emojisList,
                it.maskPosition
            )
            StickerType.Regular -> InputSticker.WithKeywords.Regular(
                it.sticker,
                it.format,
                it.emojisList,
                it.keywords
            )
            is StickerType.Unknown -> InputSticker.WithKeywords.Regular(
                it.sticker,
                it.format,
                it.emojisList,
                it.keywords
            )
        }
    },
) {
    @Serializable
    data class SurrogateInputSticker internal constructor(
        @SerialName(stickerField)
        val sticker: InputFile,
        @SerialName(formatField)
        val format: StickerFormat,
        @SerialName(emojiListField)
        val emojisList: List<String>,
        @SerialName(keywordsField)
        val keywords: List<String> = emptyList(),
        @SerialName(maskPositionField)
        val maskPosition: MaskPosition? = null,
        internal val internalType: StickerType = StickerType.Unknown()
    )
}
