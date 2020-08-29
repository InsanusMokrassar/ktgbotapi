package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InputMessageContent.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(InputMessageContent::class)
internal object InputMessageContentSerializer : KSerializer<InputMessageContent> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor(InputMessageContent::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: InputMessageContent) {
        when (value) {
            is InputContactMessageContent -> InputContactMessageContent.serializer().serialize(encoder, value)
            is InputLocationMessageContent -> InputLocationMessageContent.serializer().serialize(encoder, value)
            is InputTextMessageContent -> InputTextMessageContent.serializer().serialize(encoder, value)
            is InputVenueMessageContent -> InputVenueMessageContent.serializer().serialize(encoder, value)
            else -> throw IllegalArgumentException("Unknown for serializing InputContactMessageContent")
        }
    }

    override fun deserialize(decoder: Decoder): InputMessageContent = throw IllegalStateException("Object can't be deserialized")
}
