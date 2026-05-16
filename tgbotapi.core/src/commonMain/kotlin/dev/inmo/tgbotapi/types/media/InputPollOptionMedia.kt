package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(InputPollOptionMediaSerializer::class)
@ClassCastsIncluded
sealed interface InputPollOptionMedia {
    val type: String
}

@OptIn(ExperimentalSerializationApi::class)
@RiskFeature
object InputPollOptionMediaSerializer : KSerializer<InputPollOptionMedia> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(InputPollOptionMedia::class.toString(), PolymorphicKind.OPEN)

    override fun serialize(encoder: Encoder, value: InputPollOptionMedia) {
        when (value) {
            is TelegramMediaAnimation -> TelegramMediaAnimation.serializer().serialize(encoder, value)
            // TODO::Add TelegramMediaLivePhoto
            is TelegramMediaLocation -> TelegramMediaLocation.serializer().serialize(encoder, value)
            is TelegramMediaPhoto -> TelegramMediaPhoto.serializer().serialize(encoder, value)
            is TelegramMediaSticker -> TelegramMediaSticker.serializer().serialize(encoder, value)
            is TelegramMediaVenue -> TelegramMediaVenue.serializer().serialize(encoder, value)
            is TelegramMediaVideo -> TelegramMediaVideo.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): InputPollOptionMedia {
        throw IllegalStateException("InputPollOptionMedia can't be deserialized")
    }
}
