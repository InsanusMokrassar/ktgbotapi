@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.invoicePayloadField
import dev.inmo.tgbotapi.types.stateField
import dev.inmo.tgbotapi.types.userField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

/**
 * Information about changes to a user payment subscription toward the current bot
 *
 * @param user User who subscribed for payments toward the bot
 * @param invoicePayload Bot-specified invoice payload
 * @param state New state of the subscription
 */
@Serializable
data class BotSubscriptionUpdated(
    @SerialName(userField)
    override val user: User,
    @SerialName(invoicePayloadField)
    val invoicePayload: String,
    @SerialName(stateField)
    val state: State
) : WithUser {
    @Serializable(StateSerializer::class)
    sealed interface State {
        val name: String

        @Serializable
        data object Canceled : State {
            @EncodeDefault
            override val name = "canceled"
        }

        @Serializable
        data object Active : State {
            @EncodeDefault
            override val name = "active"
        }

        @Serializable
        data object Failed : State {
            @EncodeDefault
            override val name = "failed"
        }

        @Serializable
        @JvmInline
        value class Unknown(override val name: String) : State
    }

    private object StateSerializer : KSerializer<State> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            serialName = "dev.inmo.tgbotapi.types.payments.BotSubscriptionUpdated.State",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): State {
            val value = decoder.decodeString()
            return when (value) {
                State.Canceled.name -> State.Canceled
                State.Active.name -> State.Active
                State.Failed.name -> State.Failed
                else -> State.Unknown(value)
            }
        }

        override fun serialize(encoder: Encoder, value: State) {
            encoder.encodeString(value.name)
        }
    }
}
