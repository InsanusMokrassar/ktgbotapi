package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmName


/**
 * Represent Telegram Bots API abstraction [GiftInfo](https://core.telegram.org/bots/api#giftinfo) and
 * [UniqueGiftInfo](https://core.telegram.org/bots/api#uniquegiftinfo)
 *
 * @see ReceivedInBusinessAccount
 * @see Regular.Common
 * @see Regular.ReceivedInBusinessAccount
 * @see Unique.Common
 * @see Unique.ReceivedInBusinessAccount
 */
@Serializable
sealed interface GiftSentOrReceived : CommonEvent {
    val ownedGiftId: GiftId?
    val gift: Gift

    @Serializable
    sealed interface ReceivedInBusinessAccount : GiftSentOrReceived {
        override val ownedGiftId: GiftId
    }

    @Serializable(Regular.Companion::class)
    sealed interface Regular : GiftSentOrReceived, TextedInput {
        override val gift: Gift.Regular
        val convertStarCount: Int?
        val prepaidUpgradeStarCount: Int?
        val canBeUpgraded: Boolean
        val isPrivate: Boolean

        @Serializable
        data class Common(
            @SerialName(giftField)
            override val gift: Gift.Regular,
            @SerialName(convertStarCountField)
            override val convertStarCount: Int? = null,
            @SerialName(prepaidUpgradeStarCountField)
            override val prepaidUpgradeStarCount: Int? = null,
            @SerialName(canBeUpgradedField)
            override val canBeUpgraded: Boolean = false,
            @SerialName(textField)
            override val text: String? = null,
            @SerialName(entitiesField)
            private val entities: RawMessageEntities? = null,
            @SerialName(isPrivateField)
            override val isPrivate: Boolean = false
        ) : Regular {
            override val textSources: List<TextSource> by lazy {
                entities ?.asTextSources(text ?: return@lazy emptyList()) ?: emptyList()
            }
            override val ownedGiftId: GiftId?
                get() = null
        }

        @Serializable
        data class ReceivedInBusinessAccount(
            @SerialName(giftField)
            override val gift: Gift.Regular,
            @SerialName(ownedGiftIdField)
            override val ownedGiftId: GiftId,
            @SerialName(convertStarCountField)
            override val convertStarCount: Int? = null,
            @SerialName(prepaidUpgradeStarCountField)
            override val prepaidUpgradeStarCount: Int? = null,
            @SerialName(canBeUpgradedField)
            override val canBeUpgraded: Boolean = false,
            @SerialName(textField)
            override val text: String? = null,
            @SerialName(entitiesField)
            private val entities: RawMessageEntities? = null,
            @SerialName(isPrivateField)
            override val isPrivate: Boolean = false
        ) : Regular, GiftSentOrReceived.ReceivedInBusinessAccount {
            override val textSources: List<TextSource> by lazy {
                entities ?.asTextSources(text ?: return@lazy emptyList()) ?: emptyList()
            }
        }

        companion object : KSerializer<GiftSentOrReceived.Regular> {
            @Serializable
            private data class Surrogate(
                @SerialName(giftField)
                val gift: Gift.Regular,
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
                val entities: RawMessageEntities? = null,
                @SerialName(isPrivateField)
                val isPrivate: Boolean = false
            )

            override val descriptor: SerialDescriptor
                get() = Surrogate.serializer().descriptor

            override fun serialize(encoder: Encoder, value: Regular) {
                when (value) {
                    is Common -> Common.serializer().serialize(encoder, value)
                    is ReceivedInBusinessAccount -> ReceivedInBusinessAccount.serializer().serialize(encoder, value)
                }
            }

            override fun deserialize(decoder: Decoder): Regular {
                val surrogate = Surrogate.serializer().deserialize(decoder)

               return when {
                    surrogate.ownedGiftId == null -> {
                        Common(
                            gift = surrogate.gift,
                            convertStarCount = surrogate.convertStarCount,
                            prepaidUpgradeStarCount = surrogate.prepaidUpgradeStarCount,
                            canBeUpgraded = surrogate.canBeUpgraded,
                            text = surrogate.text,
                            entities = surrogate.entities,
                            isPrivate = surrogate.isPrivate
                        )
                    }
                    else -> {
                        ReceivedInBusinessAccount(
                            gift = surrogate.gift,
                            ownedGiftId = surrogate.ownedGiftId,
                            convertStarCount = surrogate.convertStarCount,
                            prepaidUpgradeStarCount = surrogate.prepaidUpgradeStarCount,
                            canBeUpgraded = surrogate.canBeUpgraded,
                            text = surrogate.text,
                            entities = surrogate.entities,
                            isPrivate = surrogate.isPrivate
                        )
                    }
                }
            }
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
            ) = ownedGiftId ?.let {
                ReceivedInBusinessAccount(
                    gift,
                    ownedGiftId,
                    convertStarCount,
                    prepaidUpgradeStarCount,
                    canBeUpgraded,
                    text,
                    textSources.toRawMessageEntities(position),
                    isPrivate
                )
            } ?: Common(
                gift,
                convertStarCount,
                prepaidUpgradeStarCount,
                canBeUpgraded,
                text,
                textSources.toRawMessageEntities(position),
                isPrivate
            )
        }
    }

    @Serializable(Unique.Companion::class)
    sealed interface Unique : GiftSentOrReceived {
        override val gift: Gift.Unique
        val origin: String?
        val transferStarCount: Int?

        @Serializable
        data class Common(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(originField)
            override val origin: String? = null,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null
        ) : Unique {
            override val ownedGiftId: GiftId?
                get() = null
        }

        @Serializable
        data class ReceivedInBusinessAccount(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(ownedGiftIdField)
            override val ownedGiftId: GiftId,
            @SerialName(originField)
            override val origin: String? = null,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null
        ) : Unique, GiftSentOrReceived.ReceivedInBusinessAccount

        companion object : KSerializer<GiftSentOrReceived.Unique> {
            @Serializable
            private data class Surrogate(
                @SerialName(giftField)
                val gift: Gift.Unique,
                @SerialName(ownedGiftIdField)
                val ownedGiftId: GiftId? = null,
                @SerialName(originField)
                val origin: String? = null,
                @SerialName(transferStarCountField)
                val transferStarCount: Int? = null
            )

            override val descriptor: SerialDescriptor
                get() = Surrogate.serializer().descriptor

            override fun serialize(encoder: Encoder, value: Unique) {
                when (value) {
                    is Common -> Common.serializer().serialize(encoder, value)
                    is ReceivedInBusinessAccount -> ReceivedInBusinessAccount.serializer().serialize(encoder, value)
                }
            }

            override fun deserialize(decoder: Decoder): Unique {
                val surrogate = Surrogate.serializer().deserialize(decoder)

                return when {
                    surrogate.ownedGiftId == null -> {
                        Common(
                            gift = surrogate.gift,
                            origin = surrogate.origin,
                            transferStarCount = surrogate.transferStarCount
                        )
                    }
                    else -> {
                        ReceivedInBusinessAccount(
                            gift = surrogate.gift,
                            ownedGiftId = surrogate.ownedGiftId,
                            origin = surrogate.origin,
                            transferStarCount = surrogate.transferStarCount
                        )
                    }
                }
            }
            @JvmName("PublicConstructor")
            operator fun invoke(
                gift: Gift.Unique,
                origin: String? = null,
                ownedGiftId: GiftId? = null,
                transferStarCount: Int? = null
            ) = ownedGiftId ?.let {
                ReceivedInBusinessAccount(
                    gift,
                    ownedGiftId,
                    origin,
                    transferStarCount,
                )
            } ?: Common(
                gift,
                origin,
                transferStarCount,
            )
        }
    }
}
