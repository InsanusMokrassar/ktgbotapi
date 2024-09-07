package dev.inmo.tgbotapi.types.giveaway

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PublicChatEvent
import dev.inmo.tgbotapi.types.prizeStarCountField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(GiveawayCreated.Serializer::class)
sealed interface GiveawayCreated : ChatEvent, PublicChatEvent {
    val prizeStarCount: Int?
    @Serializable
    data class Stars(
        @SerialName(prizeStarCountField)
        override val prizeStarCount: Int
    ) : GiveawayCreated

    object Serializer : KSerializer<GiveawayCreated> {
        @Serializable
        private data class Surrogate(
            @SerialName(prizeStarCountField)
            val prizeStarCount: Int? = null
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): GiveawayCreated {
            val surrogate = Surrogate.serializer().deserialize(decoder)
            return when {
                surrogate.prizeStarCount == null -> Common
                else -> Stars(surrogate.prizeStarCount)
            }
        }

        override fun serialize(encoder: Encoder, value: GiveawayCreated) {
            Surrogate.serializer().serialize(
                encoder,
                Surrogate(
                    value.prizeStarCount
                )
            )
        }

    }

    companion object : GiveawayCreated {
        val Common = this
        override val prizeStarCount: Int?
            get() = null
    }
}
