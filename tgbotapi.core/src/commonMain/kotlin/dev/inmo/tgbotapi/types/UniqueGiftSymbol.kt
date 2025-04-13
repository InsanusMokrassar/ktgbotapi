package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.files.Sticker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object describes the symbol shown on the pattern of a unique gift.
 *
 * @param name Name of the symbol
 * @param sticker The sticker that represents the unique gift
 * @param rarityPerMille The number of unique gifts that receive this model for every 1000 gifts upgraded
 */
@Serializable
data class UniqueGiftSymbol(
    @SerialName(nameField)
    val name: String,
    @SerialName(stickerField)
    val sticker: Sticker,
    @SerialName(rarityPerMilleField)
    val rarityPerMille: Int
)
