package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
sealed interface ChannelDirectMessagesConfigurationChanged : ChannelEvent {
    val enabled: Boolean
    val cost: Int?
    @Serializable
    object Disabled : ChannelDirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = false
        override val cost: Int?
            get() = null
    }
    @Serializable
    object Free : ChannelDirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = true
        override val cost: Int
            get() = 0
    }
    @Serializable
    data class Paid(
        override val cost: Int
    ) : ChannelDirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = true
    }

    companion object : KSerializer<ChannelDirectMessagesConfigurationChanged> {
        @Serializable
        private data class RawDirectMessagePriceChanged(
            val are_direct_messages_enabled: Boolean = false,
            val direct_message_star_count: Int? = null
        )
        override val descriptor: SerialDescriptor
            get() = RawDirectMessagePriceChanged.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: ChannelDirectMessagesConfigurationChanged
        ) {
            RawDirectMessagePriceChanged.serializer().serialize(
                encoder,
                RawDirectMessagePriceChanged(
                    value.enabled,
                    value.cost
                )
            )
        }

        override fun deserialize(decoder: Decoder): ChannelDirectMessagesConfigurationChanged {
            val raw = RawDirectMessagePriceChanged.serializer().deserialize(decoder)

            return when {
                raw.are_direct_messages_enabled == false -> Disabled
                raw.direct_message_star_count == null || raw.direct_message_star_count == 0 -> Free
                else -> Paid(
                    raw.direct_message_star_count
                )
            }
        }

    }
}
