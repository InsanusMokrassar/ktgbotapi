package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@RiskFeature
object InputMediaSerializer : KSerializer<InputMedia> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor(InputMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: InputMedia) {
        when (value) {
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, value)
            is InputMediaAudio -> InputMediaAudio.serializer().serialize(encoder, value)
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, value)
            is InputMediaAnimation -> InputMediaAnimation.serializer().serialize(encoder, value)
            is InputMediaDocument -> InputMediaDocument.serializer().serialize(encoder, value)
            else -> throw IllegalArgumentException("Can't perform and serialize $value")
        }
    }

    override fun deserialize(decoder: Decoder): InputMedia {
        throw IllegalStateException("Object can't be deserialized")
    }
}
