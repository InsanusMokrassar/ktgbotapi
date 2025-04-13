package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UniqueGift(
    @SerialName(baseNameField)
    val baseName: String,
    @SerialName(nameField)
    val name: String,
    @SerialName(numberField)
    val number: Int,
    @SerialName(modelField)
    val model: UniqueGiftModel,
    @SerialName(symbolField)
    val symbol: UniqueGiftSymbol,
    @SerialName(backdropField)
    val backdrop: UniqueGiftBackdrop
)
