package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.requestIdField
import dev.inmo.tgbotapi.types.userIsBotField
import dev.inmo.tgbotapi.types.userIsPremiumField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(KeyboardButtonRequestUser.Companion::class)
sealed interface KeyboardButtonRequestUser {
    val requestId: RequestId
    val isBot: Boolean?

    @Serializable
    data class Any(
        @SerialName(requestIdField)
        override val requestId: RequestId
    ) : KeyboardButtonRequestUser {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean? = null
    }

    @Serializable
    data class Common(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(userIsPremiumField)
        val isPremium: Boolean? = null
    ) : KeyboardButtonRequestUser {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = false
    }

    @Serializable
    data class Bot(
        @SerialName(requestIdField)
        override val requestId: RequestId
    ) : KeyboardButtonRequestUser {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = true
    }

    @Serializer(KeyboardButtonRequestUser::class)
    companion object : KSerializer<KeyboardButtonRequestUser> {
        @Serializable
        private data class Surrogate(
            @SerialName(requestIdField)
            val requestId: RequestId,
            @SerialName(userIsBotField)
            val userIsBot: Boolean? = null,
            @SerialName(userIsPremiumField)
            val userIsPremium: Boolean? = null
        )
        private val realSerializer = Surrogate.serializer()

        override val descriptor: SerialDescriptor = realSerializer.descriptor

        override fun deserialize(decoder: Decoder): KeyboardButtonRequestUser {
            val surrogate = realSerializer.deserialize(decoder)

            return when (surrogate.userIsBot) {
                true -> Bot(surrogate.requestId)
                false -> Common(surrogate.requestId, surrogate.userIsPremium)
                null -> Any(surrogate.requestId)
            }
        }

        override fun serialize(encoder: Encoder, value: KeyboardButtonRequestUser) {
            realSerializer.serialize(
                encoder,
                Surrogate(
                    value.requestId,
                    value.isBot,
                    (value as? Common) ?.isPremium
                )
            )
        }
    }
}
