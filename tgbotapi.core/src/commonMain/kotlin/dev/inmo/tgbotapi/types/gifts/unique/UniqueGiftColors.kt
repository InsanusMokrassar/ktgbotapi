package dev.inmo.tgbotapi.types.gifts.unique

import dev.inmo.tgbotapi.types.baseColorField
import dev.inmo.tgbotapi.types.linkColorField
import dev.inmo.tgbotapi.types.nameColorField
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UniqueGiftColors(
    @SerialName(nameColorField)
    val nameColor: RGBColor,
    @SerialName(baseColorField)
    val baseColor: RGBColor,
    @SerialName(linkColorField)
    val linkColor: RGBColor
)
