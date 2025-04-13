package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.rarityPerMilleField
import dev.inmo.tgbotapi.types.stickerField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object describes the model of a unique gift.
 *
 * @param name Name of the model
 * @param sticker The sticker that represents the unique gift
 * @param rarityPerMille The number of unique gifts that receive this model for every 1000 gifts upgraded
 */
@Serializable
data class UniqueGiftModel(
    @SerialName(nameField)
    val name: String,
    @SerialName(stickerField)
    val sticker: Sticker,
    @SerialName(rarityPerMilleField)
    val rarityPerMille: Int
)
