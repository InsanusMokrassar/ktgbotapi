package com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementSerializer

interface Update {
    val updateId: UpdateIdentifier
    val data: Any
}

data class UnknownUpdateType(
    override val updateId: UpdateIdentifier,
    override val data: String,
    val rawJson: JsonElement
) : Update

internal object UpdateSerializerWithoutDeserialization : KSerializer<Update> {
    override val descriptor: SerialDescriptor = JsonElementSerializer.descriptor

    override fun deserialize(decoder: Decoder): Update = UpdateDeserializationStrategy.deserialize(decoder)

    override fun serialize(encoder: Encoder, value: Update) = throw UnsupportedOperationException()
}

internal object UpdateDeserializationStrategy : DeserializationStrategy<Update> {
    override val descriptor: SerialDescriptor = JsonElementSerializer.descriptor

    override fun patch(decoder: Decoder, old: Update): Update = throw UpdateNotSupportedException("Update")

    override fun deserialize(decoder: Decoder): Update {
        val asJson = JsonElementSerializer.deserialize(decoder)
        return nonstrictJsonFormat.fromJson(
            RawUpdate.serializer(),
            asJson
        ).asUpdate(
            asJson
        )
    }
}
