package dev.inmo.tgbotapi.types.boosts

import dev.inmo.tgbotapi.abstracts.WithMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable(ChatBoostSource.Companion::class)
@ClassCastsIncluded
sealed interface ChatBoostSource {
    val sourceName: String
    val user: PreviewUser?

    sealed interface ByUser : ChatBoostSource {
        override val user: PreviewUser
    }

    @Serializable(ChatBoostSource.Companion::class)
    data class Premium(
        @SerialName(userField)
        override val user: PreviewUser
    ) : ByUser {
        @Required
        @EncodeDefault
        @SerialName(sourceField)
        override val sourceName: String = sourceCode

        companion object {
            const val sourceCode = "premium"
        }
    }

    @Serializable(ChatBoostSource.Companion::class)
    data class GiftCode(
        @SerialName(userField)
        override val user: PreviewUser
    ) : ByUser {
        @Required
        @EncodeDefault
        @SerialName(sourceField)
        override val sourceName: String = sourceCode

        companion object {
            const val sourceCode = "gift_code"
        }
    }

    @Serializable(ChatBoostSource.Companion::class)
    sealed interface Giveaway : ChatBoostSource, WithMessageId {
        val unclaimed: Boolean
        val claimed: Boolean
            get() = !unclaimed
        val prizeStarCount: Int?

        @Serializable(ChatBoostSource.Companion::class)
        data class Claimed(
            @SerialName(giveawayMessageIdField)
            override val messageId: MessageId,
            @SerialName(userField)
            override val user: PreviewUser,
            @SerialName(prizeStarCountField)
            override val prizeStarCount: Int?
        ) : Giveaway, ByUser {
            @Required
            @EncodeDefault
            @SerialName(sourceField)
            override val sourceName: String = Giveaway.sourceCode
            @Required
            @EncodeDefault
            @SerialName(isUnclaimedField)
            override val unclaimed: Boolean = false
        }

        @Serializable(ChatBoostSource.Companion::class)
        data class Unclaimed(
            @SerialName(giveawayMessageIdField)
            override val messageId: MessageId,
            @SerialName(prizeStarCountField)
            override val prizeStarCount: Int?
        ) : Giveaway {
            @Required
            @EncodeDefault
            @SerialName(sourceField)
            override val sourceName: String = Giveaway.sourceCode
            @Required
            @EncodeDefault
            @SerialName(isUnclaimedField)
            override val unclaimed: Boolean = true
            @SerialName(userField)
            override val user: PreviewUser? = null
        }

        companion object {
            val sourceCode = "giveaway"
        }
    }

    @Serializable(ChatBoostSource.Companion::class)
    data class Unknown(
        override val sourceName: String,
        override val user: PreviewUser?,
        val json: JsonElement?
    ) : ChatBoostSource

    @Serializable
    private data class Surrogate(
        @Required
        @EncodeDefault
        @SerialName(sourceField)
        val sourceName: String,
        @SerialName(userField)
        val user: PreviewUser? = null,
        @SerialName(giveawayMessageIdField)
        val messageId: MessageId? = null,
        @SerialName(isUnclaimedField)
        val unclaimed: Boolean? = null,
        @SerialName(prizeStarCountField)
        val prizeStarCount: Int?
    )

    companion object : KSerializer<ChatBoostSource> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): ChatBoostSource {
            val (surrogate, json) = when {
                decoder is JsonDecoder -> {
                    val json = decoder.decodeJsonElement()
                    val surrogate = decoder.json.decodeFromJsonElement(Surrogate.serializer(), json)
                    surrogate to json
                }
                else -> Surrogate.serializer().deserialize(decoder) to null
            }

            return when {
                surrogate.sourceName == Premium.sourceCode && surrogate.user != null -> {
                    Premium(surrogate.user)
                }
                surrogate.sourceName == GiftCode.sourceCode && surrogate.user != null -> {
                    GiftCode(surrogate.user)
                }
                surrogate.sourceName == Giveaway.sourceCode && surrogate.messageId != null -> {
                    when {
                        surrogate.user != null && surrogate.unclaimed == false -> Giveaway.Claimed(
                            surrogate.messageId,
                            surrogate.user,
                            surrogate.prizeStarCount
                        )
                        surrogate.unclaimed == true -> Giveaway.Unclaimed(
                            surrogate.messageId,
                            surrogate.prizeStarCount
                        )
                        else -> null
                    }
                }
                else -> null
            } ?: Unknown(surrogate.sourceName, surrogate.user, json)
        }

        override fun serialize(encoder: Encoder, value: ChatBoostSource) {
            if (value is Unknown && value.json != null) {
                JsonElement.serializer().serialize(encoder, value.json)
                return
            }

            val surrogate = Surrogate(
                value.sourceName,
                value.user,
                (value as? Giveaway) ?.messageId,
                (value as? Giveaway) ?.unclaimed,
                (value as? Giveaway) ?.prizeStarCount
            )

            Surrogate.serializer().serialize(encoder, surrogate)
        }

    }
}
