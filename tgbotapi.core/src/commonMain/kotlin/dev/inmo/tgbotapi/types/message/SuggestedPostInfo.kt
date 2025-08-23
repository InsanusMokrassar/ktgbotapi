package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.payments.SuggestedPostPrice
import dev.inmo.tgbotapi.types.stateField
import dev.inmo.tgbotapi.types.priceField
import dev.inmo.tgbotapi.types.sendDateField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

@Serializable
data class SuggestedPostInfo(
    @SerialName(stateField)
    val state: State,
    @SerialName(priceField)
    val price: SuggestedPostPrice? = null,
    @SerialName(sendDateField)
    val sendDate: Int? = null
) {
    @Serializable(State.Companion::class)
    sealed interface State {
        val name: String
        @Serializable
        data object Pending: State { override val name: String = "pending" }
        @Serializable
        data object Approved: State { override val name: String = "approved" }
        @Serializable
        data object Declined: State { override val name: String = "declined" }
        @Serializable
        @JvmInline
        value class Unknown(override val name: String) : State

        companion object : KSerializer<State> {
            override val descriptor: SerialDescriptor =
                PrimitiveSerialDescriptor("SuggestedPostInfo.State", kotlinx.serialization.descriptors.PrimitiveKind.STRING)

            override fun serialize(encoder: Encoder, value: State) {
                encoder.encodeString(value.name)
            }

            override fun deserialize(decoder: Decoder): State {
                return when (val name = decoder.decodeString()) {
                    Pending.name -> Pending
                    Approved.name -> Approved
                    Declined.name -> Declined
                    else -> Unknown(name)
                }
            }
        }
    }
}
