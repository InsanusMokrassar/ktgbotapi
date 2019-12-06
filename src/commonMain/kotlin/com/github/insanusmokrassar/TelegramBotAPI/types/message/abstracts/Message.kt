package com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.soywiz.klock.DateTime
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

interface Message {
    val messageId: MessageIdentifier
    val chat: Chat
    val date: DateTime
}

internal object TelegramBotAPIMessageDeserializationStrategy : DeserializationStrategy<Message> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("TelegramBotAPIMessageSerializer")

    override fun patch(decoder: Decoder, old: Message): Message = throw UpdateNotSupportedException(descriptor.name)
    override fun deserialize(decoder: Decoder): Message {
        return RawMessage.serializer().deserialize(decoder).asMessage
    }
}

internal object TelegramBotAPIMessageDeserializeOnlySerializer : KSerializer<Message> {
    override val descriptor: SerialDescriptor
        get() = TelegramBotAPIMessageDeserializationStrategy.descriptor

    override fun deserialize(decoder: Decoder): Message {
        return TelegramBotAPIMessageDeserializationStrategy.deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, obj: Message) {
        throw UnsupportedOperationException("Currently, Message objects can't be serialized y this serializer")
    }
}
