package dev.inmo.tgbotapi.types.gifts.unique

import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.rarityPerMilleField
import dev.inmo.tgbotapi.types.stickerField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniqueGiftSymbol(
    @SerialName(nameField)
    val name: String,
    @SerialName(stickerField)
    val sticker: Sticker,
    @SerialName(rarityPerMilleField)
    val rarityPerMille: Int,
)
