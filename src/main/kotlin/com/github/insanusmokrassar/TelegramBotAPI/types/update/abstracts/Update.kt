package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

interface Update {
    val updateId: UpdateIdentifier
    val data: Any
}

object UpdateSerializerWithoutDeserialization : KSerializer<Update> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("UpdateSerializerWithoutDeserialization")

    override fun deserialize(decoder: Decoder): Update = UpdateDeserializationStrategy.deserialize(decoder)

    override fun serialize(encoder: Encoder, obj: Update) = throw UnsupportedOperationException()
}

object UpdateDeserializationStrategy : DeserializationStrategy<Update> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("UpdateDeserializationStrategy")

    override fun patch(decoder: Decoder, old: Update): Update = throw UpdateNotSupportedException(descriptor.name)

    override fun deserialize(decoder: Decoder): Update {
        return RawUpdate.serializer().deserialize(decoder).asUpdate
    }
}
