package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GiftInfo private constructor(
    @SerialName(giftField)
    val gift: Gift,
    @SerialName(ownedGiftIdField)
    val ownedGiftId: GiftId? = null,
    @SerialName(convertStarCountField)
    val convertStarCount: Int? = null,
    @SerialName(prepaidUpgradeStarCountField)
    val prepaidUpgradeStarCount: Int? = null,
    @SerialName(canBeUpgradedField)
    val canBeUpgraded: Boolean = false,
    @SerialName(textField)
    val text: String? = null,
    @SerialName(entitiesField)
    val entities: RawMessageEntities,
    @SerialName(isPrivateField)
    val isPrivate: Boolean = false
)
