package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.colorsField
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.rarityPerMilleField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UniqueGiftBackdrop(
    @SerialName(nameField)
    val name: String,
    @SerialName(colorsField)
    val colors: UniqueGiftBackdropColors,
    @SerialName(rarityPerMilleField)
    val rarityPerMille: Int
)
