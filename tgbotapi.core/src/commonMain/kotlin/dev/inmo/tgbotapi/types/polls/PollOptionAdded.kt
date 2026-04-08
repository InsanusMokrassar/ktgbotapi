package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializerClass
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PollOptionAdded.Companion::class)
data class PollOptionAdded(
    @SerialName(pollMessageField)
    val pollMessage: Message? = null,
    @SerialName(optionPersistentIdField)
    val optionPersistentId: PollOptionPersistentId,
    @SerialName(optionTextField)
    val optionText: String,
    @SerialName(optionTextEntitiesField)
    val optionTextSources: List<TextSource> = emptyList(),
) : CommonEvent {
    companion object : KSerializer<PollOptionAdded> {
        @Serializable
        private data class Surrogate(
            @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
            @SerialName(pollMessageField)
            @Serializable(TelegramBotAPIMessageDeserializeOnlySerializerClass::class)
            val pollMessage: Message? = null,
            @SerialName(optionPersistentIdField)
            val optionPersistentId: PollOptionPersistentId,
            @SerialName(optionTextField)
            val optionText: String,
            @SerialName(optionTextEntitiesField)
            val optionTextEntities: List<RawMessageEntity> = emptyList(),
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): PollOptionAdded {
            val surrogate = Surrogate.serializer().deserialize(decoder)
            return PollOptionAdded(
                pollMessage = surrogate.pollMessage,
                optionPersistentId = surrogate.optionPersistentId,
                optionText = surrogate.optionText,
                optionTextSources = surrogate.optionTextEntities.asTextSources(surrogate.optionText),
            )
        }

        override fun serialize(encoder: Encoder, value: PollOptionAdded) {
            Surrogate.serializer().serialize(
                encoder,
                Surrogate(
                    pollMessage = value.pollMessage,
                    optionPersistentId = value.optionPersistentId,
                    optionText = value.optionText,
                    optionTextEntities = value.optionTextSources.toRawMessageEntities(),
                )
            )
        }
    }
}
