package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@RiskFeature
object TelegramMediaSerializer : KSerializer<TelegramMedia> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(TelegramMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: TelegramMedia) {
        when (value) {
            is TelegramMediaVideo -> TelegramMediaVideo.serializer().serialize(encoder, value)
            is TelegramMediaAudio -> TelegramMediaAudio.serializer().serialize(encoder, value)
            is TelegramMediaPhoto -> TelegramMediaPhoto.serializer().serialize(encoder, value)
            is TelegramMediaAnimation -> TelegramMediaAnimation.serializer().serialize(encoder, value)
            is TelegramMediaDocument -> TelegramMediaDocument.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): TelegramMedia {
        throw IllegalStateException("Object can't be deserialized")
    }
}
