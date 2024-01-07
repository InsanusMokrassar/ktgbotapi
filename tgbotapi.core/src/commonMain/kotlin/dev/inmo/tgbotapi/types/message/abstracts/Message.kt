package dev.inmo.tgbotapi.types.message.abstracts

import korlibs.time.DateTime
import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.message.RawMessage
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@ClassCastsIncluded(excludeRegex = ".*Impl")
interface Message : WithPreviewChatAndMessageId {
    val date: DateTime
}

interface AccessibleMessage : Message

@Serializable
data class InaccessibleMessage(
    override val chat: PreviewChat,
    override val messageId: MessageId,
) : AccessibleMessage {
    override val date: DateTime
        get() = DateTime.invoke(0L)
}

data class UnknownMessageType(
    override val messageId: MessageId,
    override val chat: PreviewChat,
    override val date: DateTime,
    val insideException: Exception
) : AccessibleMessage

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
