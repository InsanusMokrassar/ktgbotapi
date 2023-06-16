package dev.inmo.tgbotapi.types.message.abstracts

import korlibs.time.DateTime
import dev.inmo.tgbotapi.abstracts.WithChat
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.RawMessage
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@ClassCastsIncluded(excludeRegex = ".*Impl")
interface Message : WithChat {
    val messageId: MessageId
    val date: DateTime
}

data class UnknownMessageType(
    override val messageId: MessageId,
    override val chat: Chat,
    override val date: DateTime,
    val insideException: Exception
) : Message

internal class TelegramBotAPIMessageDeserializationStrategyClass<T> : DeserializationStrategy<T> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor("TelegramBotAPIMessageSerializer", PolymorphicKind.OPEN)

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(decoder: Decoder): T {
        return RawMessage.serializer().deserialize(decoder).asMessage as T
    }
}

internal class TelegramBotAPIMessageDeserializeOnlySerializerClass<T : Message> : KSerializer<T> {
    private val deserializer = TelegramBotAPIMessageDeserializationStrategyClass<T>()
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor
        get() = deserializer.descriptor

    override fun deserialize(decoder: Decoder): T {
        return deserializer.deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, value: T) {
        throw UnsupportedOperationException("Currently, Message objects can't be serialized y this serializer")
    }
}
internal object TelegramBotAPIMessageDeserializeOnlySerializer
    : KSerializer<Message> by TelegramBotAPIMessageDeserializeOnlySerializerClass()
