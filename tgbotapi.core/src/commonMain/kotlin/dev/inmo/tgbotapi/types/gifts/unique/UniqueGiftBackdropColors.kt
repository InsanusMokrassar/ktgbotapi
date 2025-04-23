package dev.inmo.tgbotapi.types.gifts.unique

import dev.inmo.tgbotapi.types.centerColorField
import dev.inmo.tgbotapi.types.edgeColorField
import dev.inmo.tgbotapi.types.symbolColorField
import dev.inmo.tgbotapi.types.textColorField
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniqueGiftBackdropColors(
    @SerialName(centerColorField)
    val centerColor: RGBColor,
    @SerialName(edgeColorField)
    val edgeColor: RGBColor,
    @SerialName(symbolColorField)
    val symbolColor: RGBColor,
    @SerialName(textColorField)
    val textColor: RGBColor,
)
