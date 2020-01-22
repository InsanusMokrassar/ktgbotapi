package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElementSerializer

interface Update {
    val updateId: UpdateIdentifier
    val data: Any
}

internal object UpdateSerializerWithoutDeserialization : KSerializer<Update> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("UpdateSerializerWithoutDeserialization")

    override fun deserialize(decoder: Decoder): Update = UpdateDeserializationStrategy.deserialize(decoder)

    override fun serialize(encoder: Encoder, obj: Update) = throw UnsupportedOperationException()
}

internal object UpdateDeserializationStrategy : DeserializationStrategy<Update> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("UpdateDeserializationStrategy")

    override fun patch(decoder: Decoder, old: Update): Update = throw UpdateNotSupportedException(descriptor.name)

    override fun deserialize(decoder: Decoder): Update {
        val asJson = JsonElementSerializer.deserialize(decoder)
        return Json.nonstrict.fromJson(
            RawUpdate.serializer(),
            asJson
        ).asUpdate(
            asJson.toString()
        )
    }
}
