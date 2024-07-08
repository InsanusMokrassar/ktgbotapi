package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@RiskFeature
object MediaGroupMemberTelegramMediaSerializer : KSerializer<MediaGroupMemberTelegramMedia> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(MediaGroupMemberTelegramMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: MediaGroupMemberTelegramMedia) {
        when (value) {
            is TelegramMediaPhoto -> TelegramMediaPhoto.serializer().serialize(encoder, value)
            is TelegramMediaVideo -> TelegramMediaVideo.serializer().serialize(encoder, value)
            is TelegramMediaAudio -> TelegramMediaAudio.serializer().serialize(encoder, value)
            is TelegramMediaDocument -> TelegramMediaDocument.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): MediaGroupMemberTelegramMedia {
        val json = JsonObject.serializer().deserialize(decoder)

        return when (json[typeField] ?.jsonPrimitive ?.contentOrNull) {
            photoTelegramMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramMediaPhoto.serializer(), json)
            videoTelegramMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramMediaVideo.serializer(), json)
            audioTelegramMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramMediaAudio.serializer(), json)
            documentTelegramMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramMediaDocument.serializer(), json)
            else -> error("Illegal type of incoming MediaGroupMemberTelegramMedia")
        }
    }
}
