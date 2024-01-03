package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(KeyboardButtonRequestUsers.Companion::class)
@ClassCastsIncluded
sealed interface KeyboardButtonRequestUsers {
    val requestId: RequestId
    val isBot: Boolean?
    val maxCount: Int

    @Serializable
    data class Any(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean? = null
    }

    @Serializable
    data class Common(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(userIsPremiumField)
        val isPremium: Boolean? = null,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = false
    }

    @Serializable
    data class Bot(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = true
    }

    @Serializer(KeyboardButtonRequestUsers::class)
    companion object : KSerializer<KeyboardButtonRequestUsers> {
        @Serializable
        private data class Surrogate(
            @SerialName(requestIdField)
            val requestId: RequestId,
            @SerialName(userIsBotField)
            val userIsBot: Boolean? = null,
            @SerialName(userIsPremiumField)
            val userIsPremium: Boolean? = null,
            @SerialName(maxQuantityField)
            val maxCount: Int = keyboardButtonRequestUserLimit.first
        )
        private val realSerializer = Surrogate.serializer()

        override val descriptor: SerialDescriptor = realSerializer.descriptor

        override fun deserialize(decoder: Decoder): KeyboardButtonRequestUsers {
            val surrogate = realSerializer.deserialize(decoder)

            return when (surrogate.userIsBot) {
                true -> Bot(surrogate.requestId, surrogate.maxCount)
                false -> Common(surrogate.requestId, surrogate.userIsPremium, surrogate.maxCount)
                null -> Any(surrogate.requestId, surrogate.maxCount)
            }
        }

        override fun serialize(encoder: Encoder, value: KeyboardButtonRequestUsers) {
            realSerializer.serialize(
                encoder,
                Surrogate(
                    value.requestId,
                    value.isBot,
                    (value as? Common) ?.isPremium,
                    value.maxCount
                )
            )
        }
    }
}

@Deprecated("Renamed", ReplaceWith("KeyboardButtonRequestUsers", "dev.inmo.tgbotapi.types.buttons.KeyboardButtonRequestUsers"))
typealias KeyboardButtonRequestUser = KeyboardButtonRequestUsers
