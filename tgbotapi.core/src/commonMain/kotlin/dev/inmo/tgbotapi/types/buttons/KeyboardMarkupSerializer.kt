package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@RiskFeature
object KeyboardMarkupSerializer : KSerializer<KeyboardMarkup> {
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(
        KeyboardMarkup::class.toString(),
        PolymorphicKind.OPEN
    )
    override fun serialize(encoder: Encoder, value: KeyboardMarkup) {
        when(value) {
            is ReplyForce -> ReplyForce.serializer().serialize(encoder, value)
            is InlineKeyboardMarkup -> InlineKeyboardMarkup.serializer().serialize(encoder, value)
            is ReplyKeyboardMarkup -> ReplyKeyboardMarkup.serializer().serialize(encoder, value)
            is ReplyKeyboardRemove -> ReplyKeyboardRemove.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): KeyboardMarkup {
        TODO("not implemented")
    }
}
