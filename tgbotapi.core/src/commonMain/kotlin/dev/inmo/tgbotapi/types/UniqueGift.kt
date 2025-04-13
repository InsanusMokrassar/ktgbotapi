package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object describes a unique gift that was upgraded from a regular gift.
 *
 * @param baseName Human-readable name of the regular gift from which this unique gift was upgraded
 * @param name Unique name of the gift. This name can be used in `https://t.me/nft/...` links and story areas
 * @param number Unique number of the upgraded gift among gifts upgraded from the same regular gift
 * @param model Model of the gift
 * @param symbol Symbol of the gift
 * @param backdrop Backdrop of the gift
 */
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
