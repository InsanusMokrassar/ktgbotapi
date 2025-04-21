package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmName


@Serializable
sealed interface GiftInfo {
    val ownedGiftId: GiftId?
    val gift: Gift

    @Serializable
    data class Regular(
        @SerialName(giftField)
        override val gift: Gift.Regular,
        @SerialName(ownedGiftIdField)
        override val ownedGiftId: GiftId? = null,
        @SerialName(convertStarCountField)
        val convertStarCount: Int? = null,
        @SerialName(prepaidUpgradeStarCountField)
        val prepaidUpgradeStarCount: Int? = null,
        @SerialName(canBeUpgradedField)
        val canBeUpgraded: Boolean = false,
        @SerialName(textField)
        val text: String? = null,
        @SerialName(entitiesField)
        val entities: RawMessageEntities = emptyList(),
        @SerialName(isPrivateField)
        val isPrivate: Boolean = false
    ) : GiftInfo {
        companion object {
            @JvmName("PublicConstructor")
            operator fun invoke(
                gift: Gift.Regular,
                ownedGiftId: GiftId? = null,
                convertStarCount: Int? = null,
                prepaidUpgradeStarCount: Int? = null,
                canBeUpgraded: Boolean = false,
                text: String? = null,
                textSources: TextSourcesList = emptyList(),
                position: Int,
                isPrivate: Boolean = false
            ) = Regular(
                gift,
                ownedGiftId,
                convertStarCount,
                prepaidUpgradeStarCount,
                canBeUpgraded,
                text,
                textSources.toRawMessageEntities(position),
                isPrivate
            )
        }
    }

    @Serializable
    data class Unique(
        @SerialName(giftField)
        override val gift: Gift.Unique,
        @SerialName(originField)
        val origin: String? = null,
        @SerialName(ownedGiftIdField)
        override val ownedGiftId: GiftId? = null,
        @SerialName(transferStarCountField)
        val transferStarCount: Int? = null
    ): GiftInfo
}
