package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.WithMessageId
import korlibs.time.DateTime
import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.message.RawMessage
import dev.inmo.tgbotapi.types.threadId
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

@ClassCastsIncluded(excludeRegex = ".*Impl")
interface Message : WithPreviewChatAndMessageId {
    val date: DateTime
    val metaInfo: MetaInfo
        get() = MetaInfo(chat.id, messageId)

    @Serializable
    @JvmInline
    value class MetaInfo(
        val chatIdMessageIdThreadId: Triple<ChatIdentifier, MessageId, MessageThreadId?>
    ) : WithMessageId {
        val chatId: ChatIdentifier
            get() = chatIdMessageIdThreadId.first
        override val messageId: MessageId
            get() = chatIdMessageIdThreadId.second
        val threadId: MessageThreadId?
            get() = chatIdMessageIdThreadId.third

        constructor(chatId: ChatIdentifier, messageId: MessageId, threadId: MessageThreadId? = chatId.threadId) : this(Triple(chatId, messageId, threadId))
        constructor(chatIdMessageId: Pair<ChatIdentifier, MessageId>, threadId: MessageThreadId? = chatIdMessageId.first.threadId) : this(Triple(chatIdMessageId.first, chatIdMessageId.second, threadId))

        fun copy(
            chatId: ChatIdentifier = this.chatId, messageId: MessageId = this.messageId, threadId: MessageThreadId? = chatId.threadId
        ) = MetaInfo(chatId, messageId, threadId)
    }
}

interface AccessibleMessage : Message

@Serializable
data class InaccessibleMessage(
    override val chat: PreviewChat,
    override val messageId: MessageId,
) : Message {
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
