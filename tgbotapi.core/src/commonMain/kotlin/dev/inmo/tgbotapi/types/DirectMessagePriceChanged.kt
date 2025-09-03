package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChannelEvent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(DirectMessagesConfigurationChanged.Companion::class)
sealed interface DirectMessagesConfigurationChanged : ChannelEvent {
    val enabled: Boolean
    val cost: Int?
    @Serializable(DirectMessagesConfigurationChanged.Companion::class)
    data object Disabled : DirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = false
        override val cost: Int?
            get() = null
    }
    @Serializable(DirectMessagesConfigurationChanged.Companion::class)
    data object Free : DirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = true
        override val cost: Int
            get() = 0
    }
    @Serializable(DirectMessagesConfigurationChanged.Companion::class)
    data class Paid(
        override val cost: Int
    ) : DirectMessagesConfigurationChanged {
        override val enabled: Boolean
            get() = true
    }

    companion object : KSerializer<DirectMessagesConfigurationChanged> {
        @Serializable
        private data class RawDirectMessagePriceChanged(
            val are_direct_messages_enabled: Boolean = false,
            val direct_message_star_count: Int? = null
        )
        override val descriptor: SerialDescriptor
            get() = RawDirectMessagePriceChanged.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: DirectMessagesConfigurationChanged
        ) {
            RawDirectMessagePriceChanged.serializer().serialize(
                encoder,
                RawDirectMessagePriceChanged(
                    value.enabled,
                    value.cost
                )
            )
        }

        override fun deserialize(decoder: Decoder): DirectMessagesConfigurationChanged {
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
