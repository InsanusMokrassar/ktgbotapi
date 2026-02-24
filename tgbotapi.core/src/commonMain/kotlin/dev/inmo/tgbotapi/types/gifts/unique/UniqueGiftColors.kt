package dev.inmo.tgbotapi.types.gifts.unique

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UniqueGiftColors(
    @SerialName(modelCustomEmojiIdField)
    val modelCustomEmojiId: CustomEmojiId,
    @SerialName(symbolCustomEmojiIdField)
    val symbolCustomEmojiId: CustomEmojiId,
    @SerialName(lightThemeMainColorField)
    val lightThemeMainColor: RGBColor,
    @SerialName(lightThemeOtherColorsField)
    val lightThemeOtherColors: List<RGBColor>,
    @SerialName(darkThemeMainColorField)
    val darkThemeMainColor: RGBColor,
    @SerialName(darkThemeOtherColorsField)
    val darkThemeOtherColors: List<RGBColor>
)
