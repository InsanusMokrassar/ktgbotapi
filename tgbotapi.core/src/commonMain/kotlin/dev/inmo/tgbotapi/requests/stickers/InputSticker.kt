package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.types.emojiListField
import dev.inmo.tgbotapi.types.keywordsField
import dev.inmo.tgbotapi.types.maskPositionField
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.types.stickers.MaskPosition
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ClassCastsIncluded
@Serializable
sealed interface InputSticker {
    val sticker: InputFile
    val emojisList: List<String>

    @Serializable
    data class Mask(
        @SerialName(stickerField)
        override val sticker: InputFile,
        @SerialName(emojiListField)
        override val emojisList: List<String>,
        @SerialName(maskPositionField)
        val maskPosition: MaskPosition
    ) : InputSticker

    @Serializable
    sealed interface WithKeywords : InputSticker {
        val keywords: List<String>

        @Serializable
        data class Regular(
            @SerialName(stickerField)
            override val sticker: InputFile,
            @SerialName(emojiListField)
            override val emojisList: List<String>,
            @SerialName(keywordsField)
            override val keywords: List<String>
        ) : WithKeywords

        @Serializable
        data class CustomEmoji(
            @SerialName(stickerField)
            override val sticker: InputFile,
            @SerialName(emojiListField)
            override val emojisList: List<String>,
            @SerialName(keywordsField)
            override val keywords: List<String>
        ) : WithKeywords
    }
}
