package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object describes the backdrop of a unique gift.
 *
 * @param name Name of the backdrop
 * @param colors Colors of the backdrop
 * @param rarityPerMille The number of unique gifts that receive this backdrop for every 1000 gifts upgraded
 */
@Serializable
data class UniqueGiftBackdrop(
    @SerialName(nameField)
    val name: String,
    @SerialName(colorsField)
    val colors: UniqueGiftBackdropColors,
    @SerialName(rarityPerMilleField)
    val rarityPerMille: Int
)
