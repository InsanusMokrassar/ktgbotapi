package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName

@Serializable(OwnedGift.Companion::class)
sealed interface OwnedGift {
    val gift: Gift
    val sendDate: TelegramDate
    val ownedGiftId: GiftId?
    val senderUser: PreviewUser?
    val isSaved: Boolean

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(OwnedGift.Companion::class)
    sealed interface OwnedByBusinessAccount : OwnedGift {
        override val ownedGiftId: GiftId
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(OwnedGift.Companion::class)
    sealed interface Common : OwnedGift {
        override val ownedGiftId: GiftId?
            get() = null
    }

    @Serializable(Regular.Companion::class)
    sealed interface Regular : OwnedGift, TextedInput {
        override val gift: Gift.Regular
        val isPrivate: Boolean
        val canBeUpgraded: Boolean
        val wasRefunded: Boolean
        val convertStarCount: Int?
        val prepaidUpgradeStarCount: Int?
        val isUpgradeSeparate: Boolean
        val uniqueGiftNumber: Int?

        @Serializable
        data class Common(
            @SerialName(giftField)
            override val gift: Gift.Regular,
            @SerialName(sendDateField)
            override val sendDate: TelegramDate,
            @SerialName(senderUserField)
            override val senderUser: PreviewUser? = null,
            @SerialName(textField)
            override val text: String? = null,
            @SerialName(entitiesField)
            private val entities: RawMessageEntities? = null,
            @SerialName(isPrivateField)
            override val isPrivate: Boolean = false,
            @SerialName(isSavedField)
            override val isSaved: Boolean = false,
            @SerialName(canBeUpgradedField)
            override val canBeUpgraded: Boolean = false,
            @SerialName(wasRefundedField)
            override val wasRefunded: Boolean = false,
            @SerialName(convertStarCountField)
            override val convertStarCount: Int? = null,
            @SerialName(prepaidUpgradeStarCountField)
            override val prepaidUpgradeStarCount: Int? = null,
            @SerialName(isUpgradeSeparateField)
            override val isUpgradeSeparate: Boolean = false,
            @SerialName(uniqueGiftNumberField)
            override val uniqueGiftNumber: Int? = null
        ) : Regular, OwnedGift.Common {
            override val textSources: List<TextSource> by lazy {
                entities ?.asTextSources(text ?: return@lazy emptyList()) ?: emptyList()
            }
        }

        @Serializable
        data class OwnedByBusinessAccount(
            @SerialName(giftField)
            override val gift: Gift.Regular,
            @SerialName(sendDateField)
            override val sendDate: TelegramDate,
            @SerialName(ownedGiftIdField)
            override val ownedGiftId: GiftId,
            @SerialName(senderUserField)
            override val senderUser: PreviewUser? = null,
            @SerialName(textField)
            override val text: String? = null,
            @SerialName(entitiesField)
            private val entities: RawMessageEntities? = null,
            @SerialName(isPrivateField)
            override val isPrivate: Boolean = false,
            @SerialName(isSavedField)
            override val isSaved: Boolean = false,
            @SerialName(canBeUpgradedField)
            override val canBeUpgraded: Boolean = false,
            @SerialName(wasRefundedField)
            override val wasRefunded: Boolean = false,
            @SerialName(convertStarCountField)
            override val convertStarCount: Int? = null,
            @SerialName(prepaidUpgradeStarCountField)
            override val prepaidUpgradeStarCount: Int? = null,
            @SerialName(isUpgradeSeparateField)
            override val isUpgradeSeparate: Boolean = false,
            @SerialName(uniqueGiftNumberField)
            override val uniqueGiftNumber: Int? = null
        ) : Regular, OwnedGift.OwnedByBusinessAccount {
            override val textSources: List<TextSource> by lazy {
                entities ?.asTextSources(text ?: return@lazy emptyList()) ?: emptyList()
            }
        }

        companion object : KSerializer<OwnedGift.Regular> {
            @Serializable
            private data class Surrogate(
                @SerialName(giftField)
                val gift: Gift.Regular,
                @SerialName(ownedGiftIdField)
                val ownedGiftId: GiftId? = null,
                @SerialName(senderUserField)
                val senderUser: PreviewUser? = null,
                @SerialName(sendDateField)
                val sendDate: TelegramDate,
                @SerialName(textField)
                val text: String? = null,
                @SerialName(entitiesField)
                val entities: RawMessageEntities? = null,
                @SerialName(isPrivateField)
                val isPrivate: Boolean = false,
                @SerialName(isSavedField)
                val isSaved: Boolean = false,
                @SerialName(canBeUpgradedField)
                val canBeUpgraded: Boolean = false,
                @SerialName(wasRefundedField)
                val wasRefunded: Boolean = false,
                @SerialName(convertStarCountField)
                val convertStarCount: Int? = null,
                @SerialName(prepaidUpgradeStarCountField)
                val prepaidUpgradeStarCount: Int? = null,
                @SerialName(isUpgradeSeparateField)
                val isUpgradeSeparate: Boolean = false,
                @SerialName(uniqueGiftNumberField)
                val uniqueGiftNumber: Int? = null
            )

            override val descriptor: SerialDescriptor
                get() = Surrogate.serializer().descriptor

            override fun serialize(encoder: Encoder, value: Regular) {
                when (value) {
                    is Common -> Common.serializer().serialize(encoder, value)
                    is OwnedByBusinessAccount -> OwnedByBusinessAccount.serializer().serialize(encoder, value)
                }
            }

            override fun deserialize(decoder: Decoder): Regular {
                val surrogate = Surrogate.serializer().deserialize(decoder)

                return when {
                    surrogate.ownedGiftId == null -> {
                        Common(
                            gift = surrogate.gift,
                            sendDate = surrogate.sendDate,
                            senderUser = surrogate.senderUser,
                            text = surrogate.text,
                            entities = surrogate.entities,
                            isPrivate = surrogate.isPrivate,
                            isSaved = surrogate.isSaved,
                            canBeUpgraded = surrogate.canBeUpgraded,
                            wasRefunded = surrogate.wasRefunded,
                            convertStarCount = surrogate.convertStarCount,
                            prepaidUpgradeStarCount = surrogate.prepaidUpgradeStarCount,
                            isUpgradeSeparate = surrogate.isUpgradeSeparate,
                            uniqueGiftNumber = surrogate.uniqueGiftNumber
                        )
                    }
                    else -> {
                        OwnedByBusinessAccount(
                            gift = surrogate.gift,
                            sendDate = surrogate.sendDate,
                            ownedGiftId = surrogate.ownedGiftId,
                            senderUser = surrogate.senderUser,
                            text = surrogate.text,
                            entities = surrogate.entities,
                            isPrivate = surrogate.isPrivate,
                            isSaved = surrogate.isSaved,
                            canBeUpgraded = surrogate.canBeUpgraded,
                            wasRefunded = surrogate.wasRefunded,
                            convertStarCount = surrogate.convertStarCount,
                            prepaidUpgradeStarCount = surrogate.prepaidUpgradeStarCount,
                            isUpgradeSeparate = surrogate.isUpgradeSeparate,
                            uniqueGiftNumber = surrogate.uniqueGiftNumber
                        )
                    }
                }
            }
        }
    }

    @Serializable(Unique.Companion::class)
    sealed interface Unique : OwnedGift {
        override val gift: Gift.Unique
        val canBeTransferred: Boolean
        val transferStarCount: Int?
        val nextTransferDate: TelegramDate?

        @Serializable
        data class Common(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(sendDateField)
            override val sendDate: TelegramDate,
            @SerialName(senderUserField)
            override val senderUser: PreviewUser? = null,
            @SerialName(isSavedField)
            override val isSaved: Boolean = false,
            @SerialName(canBeTransferredField)
            override val canBeTransferred: Boolean = false,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
        ) : Unique, OwnedGift.Common

        @Serializable
        data class OwnedByBusinessAccount(
            @SerialName(giftField)
            override val gift: Gift.Unique,
            @SerialName(sendDateField)
            override val sendDate: TelegramDate,
            @SerialName(ownedGiftIdField)
            override val ownedGiftId: GiftId,
            @SerialName(senderUserField)
            override val senderUser: PreviewUser? = null,
            @SerialName(isSavedField)
            override val isSaved: Boolean = false,
            @SerialName(canBeTransferredField)
            override val canBeTransferred: Boolean = false,
            @SerialName(transferStarCountField)
            override val transferStarCount: Int? = null,
            @SerialName(nextTransferDateField)
            override val nextTransferDate: TelegramDate? = null
        ) : Unique, OwnedGift.OwnedByBusinessAccount

        companion object : KSerializer<OwnedGift.Unique> {
            @Serializable
            private data class Surrogate(
                @SerialName(giftField)
                val gift: Gift.Unique,
                @SerialName(ownedGiftIdField)
                val ownedGiftId: GiftId? = null,
                @SerialName(senderUserField)
                val senderUser: PreviewUser? = null,
                @SerialName(sendDateField)
                val sendDate: TelegramDate,
                @SerialName(isSavedField)
                val isSaved: Boolean = false,
                @SerialName(canBeTransferredField)
                val canBeTransferred: Boolean = false,
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
                    is OwnedByBusinessAccount -> OwnedByBusinessAccount.serializer().serialize(encoder, value)
                }
            }

            override fun deserialize(decoder: Decoder): Unique {
                val surrogate = Surrogate.serializer().deserialize(decoder)

                return when {
                    surrogate.ownedGiftId == null -> {
                        Common(
                            gift = surrogate.gift,
                            sendDate = surrogate.sendDate,
                            senderUser = surrogate.senderUser,
                            isSaved = surrogate.isSaved,
                            canBeTransferred = surrogate.canBeTransferred,
                            transferStarCount = surrogate.transferStarCount,
                            nextTransferDate = surrogate.nextTransferDate
                        )
                    }
                    else -> {
                        OwnedByBusinessAccount(
                            gift = surrogate.gift,
                            sendDate = surrogate.sendDate,
                            ownedGiftId = surrogate.ownedGiftId,
                            senderUser = surrogate.senderUser,
                            isSaved = surrogate.isSaved,
                            canBeTransferred = surrogate.canBeTransferred,
                            transferStarCount = surrogate.transferStarCount,
                            nextTransferDate = surrogate.nextTransferDate
                        )
                    }
                }
            }
        }
    }

    companion object : KSerializer<OwnedGift> {
        override val descriptor: SerialDescriptor
            get() = JsonElement.serializer().descriptor

        override fun serialize(encoder: Encoder, value: OwnedGift) {
            when (value) {
                is Regular -> Regular.serialize(encoder, value)
                is Unique -> Unique.serialize(encoder, value)
            }
        }

        override fun deserialize(decoder: Decoder): OwnedGift {
            val json = (decoder as JsonDecoder)
            val element = json.decodeJsonElement()
            val type = element.jsonObject[typeField] ?.jsonPrimitive ?.content

            return when (type) {
                "regular" -> json.json.decodeFromJsonElement(Regular, element)
                "unique" -> json.json.decodeFromJsonElement(Unique, element)
                else -> error("Unknown OwnedGift type: $type")
            }
        }
    }
}
