package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceived.Unique.Common
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName


/**
 * Represent Telegram Bots API abstraction [OwnedGiftUnique](https://core.telegram.org/bots/api#giftinfo) and
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
    val nextTransferDate: TelegramDate?

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
        val isUpgradeSeparate: Boolean
        val uniqueGiftNumber: Int?

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
            override val isPrivate: Boolean = false,
            @SerialName(isUpgradeSeparateField)
            override val isUpgradeSeparate: Boolean = false,
            @SerialName(uniqueGiftNumberField)
            override val uniqueGiftNumber: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
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
            override val isPrivate: Boolean = false,
            @SerialName(isUpgradeSeparateField)
            override val isUpgradeSeparate: Boolean = false,
            @SerialName(uniqueGiftNumberField)
            override val uniqueGiftNumber: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
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
                val isPrivate: Boolean = false,
                @SerialName(isUpgradeSeparateField)
                val isUpgradeSeparate: Boolean = false,
                @SerialName(uniqueGiftNumberField)
                val uniqueGiftNumber: Int? = null,
                @SerialName(nextTransferDateField)
                val nextTransferDate: TelegramDate? = null
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
                            isPrivate = surrogate.isPrivate,
                            isUpgradeSeparate = surrogate.isUpgradeSeparate,
                            uniqueGiftNumber = surrogate.uniqueGiftNumber,
                            nextTransferDate = surrogate.nextTransferDate
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
                            isPrivate = surrogate.isPrivate,
                            isUpgradeSeparate = surrogate.isUpgradeSeparate,
                            uniqueGiftNumber = surrogate.uniqueGiftNumber,
                            nextTransferDate = surrogate.nextTransferDate
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
                isPrivate: Boolean = false,
                isUpgradeSeparate: Boolean = false,
                uniqueGiftNumber: Int? = null
            ) = ownedGiftId ?.let {
                ReceivedInBusinessAccount(
                    gift,
                    ownedGiftId,
                    convertStarCount,
                    prepaidUpgradeStarCount,
                    canBeUpgraded,
                    text,
                    textSources.toRawMessageEntities(position),
                    isPrivate,
                    isUpgradeSeparate,
                    uniqueGiftNumber
                )
            } ?: Common(
                gift,
                convertStarCount,
                prepaidUpgradeStarCount,
                canBeUpgraded,
                text,
                textSources.toRawMessageEntities(position),
                isPrivate,
                isUpgradeSeparate,
                uniqueGiftNumber
            )
        }
    }

    @Serializable(Unique.Companion::class)
    sealed interface Unique : GiftSentOrReceived {
        override val gift: Gift.Unique
        val origin: String?
        val originTyped: Origin?
        @Deprecated("Use lastResaleCurrency and lastResaleAmount instead")
        val lastResaleStarCount: Int?
        val lastResaleCurrency: String?
        val lastResaleAmount: Long?
        val transferStarCount: Int?

        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(Origin.Companion::class)
        sealed interface Origin {
            val string: String
            @Serializable(Origin.Companion::class)
            data object Upgrade : Origin { override val string: String = "upgrade" }
            @Serializable(Origin.Companion::class)
            data object Transfer : Origin { override val string: String = "transfer" }
            @Serializable(Origin.Companion::class)
            data object Resale : Origin { override val string: String = "resale" }
            @Serializable(Origin.Companion::class)
            data object GiftedUpgrade : Origin { override val string: String = "gifted_upgrade" }
            @Serializable(Origin.Companion::class)
            data object Offer : Origin { override val string: String = "offer" }
            @Serializable(Origin.Companion::class)
            @JvmInline
            value class Unknown(override val string: String) : Origin

            companion object : KSerializer<Origin> {
                override val descriptor: SerialDescriptor = String.serializer().descriptor

                fun fromString(value: String): Origin = when (value) {
                    Upgrade.string -> Upgrade
                    Transfer.string -> Transfer
                    Resale.string -> Resale
                    GiftedUpgrade.string -> GiftedUpgrade
                    Offer.string -> Offer
                    else -> Unknown(value)
                }

                override fun deserialize(decoder: Decoder): Origin {
                    val value = decoder.decodeString()
                    return fromString(value)
                }

                override fun serialize(
                    encoder: Encoder,
                    value: Origin
                ) {
                    encoder.encodeString(value.string)
                }
            }
        }

        @Serializable
        data class Common(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(originField)
            override val originTyped: Origin? = null,
            @Deprecated("Use lastResaleCurrency and lastResaleAmount instead")
            @SerialName(lastResaleStarCountField)
            override val lastResaleStarCount: Int? = null,
            @SerialName(lastResaleCurrencyField)
            override val lastResaleCurrency: Currency? = null,
            @SerialName(lastResaleAmountField)
            override val lastResaleAmount: Long? = null,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
        ) : Unique {
            override val ownedGiftId: GiftId?
                get() = null

            @Transient
            override val origin: String? = originTyped ?.string

            constructor(
                gift: Gift.Unique,
                origin: String?,
                lastResaleStarCount: Int? = null,
                lastResaleCurrency: Currency? = null,
                lastResaleAmount: Long? = null,
                transferStarCount: Int? = null,
                nextTransferDate: TelegramDate? = null
            ) : this(
                gift,
                origin ?.let { Origin.fromString(it) },
                lastResaleStarCount,
                lastResaleCurrency,
                lastResaleAmount,
                transferStarCount,
                nextTransferDate
            )
        }

        @Serializable
        data class ReceivedInBusinessAccount(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(ownedGiftIdField)
            override val ownedGiftId: GiftId,
            @SerialName(originField)
            override val originTyped: Origin? = null,
            @Deprecated("Use lastResaleCurrency and lastResaleAmount instead")
            @SerialName(lastResaleStarCountField)
            override val lastResaleStarCount: Int? = null,
            @SerialName(lastResaleCurrencyField)
            override val lastResaleCurrency: Currency? = null,
            @SerialName(lastResaleAmountField)
            override val lastResaleAmount: Long? = null,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
        ) : Unique, GiftSentOrReceived.ReceivedInBusinessAccount {
            @Transient
            override val origin: String? = originTyped ?.string

            constructor(
                gift: Gift.Unique,
                ownedGiftId: GiftId,
                origin: String? = null,
                lastResaleStarCount: Int? = null,
                lastResaleCurrency: Currency? = null,
                lastResaleAmount: Long? = null,
                transferStarCount: Int? = null,
                nextTransferDate: TelegramDate? = null
            ) : this(
                gift = gift,
                ownedGiftId = ownedGiftId,
                originTyped = origin ?.let { Origin.fromString(it) },
                lastResaleStarCount = lastResaleStarCount,
                lastResaleCurrency = lastResaleCurrency,
                lastResaleAmount = lastResaleAmount,
                transferStarCount = transferStarCount,
                nextTransferDate = nextTransferDate
            )
        }

        companion object : KSerializer<GiftSentOrReceived.Unique> {
            @Serializable
            private data class Surrogate(
                @SerialName(giftField)
                val gift: Gift.Unique,
                @SerialName(ownedGiftIdField)
                val ownedGiftId: GiftId? = null,
                @SerialName(originField)
                val origin: Origin? = null,
                @SerialName(lastResaleStarCountField)
                val lastResaleStarCount: Int? = null,
                @SerialName(lastResaleCurrencyField)
                val lastResaleCurrency: Currency? = null,
                @SerialName(lastResaleAmountField)
                val lastResaleAmount: Long? = null,
                @SerialName(transferStarCountField)
                val transferStarCount: Int? = null,
                @SerialName(nextTransferDateField)
                val nextTransferDate: TelegramDate? = null
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
                            originTyped = surrogate.origin,
                            lastResaleStarCount = surrogate.lastResaleStarCount,
                            lastResaleCurrency = surrogate.lastResaleCurrency,
                            lastResaleAmount = surrogate.lastResaleAmount,
                            transferStarCount = surrogate.transferStarCount,
                            nextTransferDate = surrogate.nextTransferDate
                        )
                    }
                    else -> {
                        ReceivedInBusinessAccount(
                            gift = surrogate.gift,
                            ownedGiftId = surrogate.ownedGiftId,
                            originTyped = surrogate.origin,
                            lastResaleStarCount = surrogate.lastResaleStarCount,
                            lastResaleCurrency = surrogate.lastResaleCurrency,
                            lastResaleAmount = surrogate.lastResaleAmount,
                            transferStarCount = surrogate.transferStarCount,
                            nextTransferDate = surrogate.nextTransferDate
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
