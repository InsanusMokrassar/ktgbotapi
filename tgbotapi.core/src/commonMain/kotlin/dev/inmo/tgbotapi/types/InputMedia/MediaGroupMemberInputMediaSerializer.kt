package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@RiskFeature
object MediaGroupMemberInputMediaSerializer : KSerializer<MediaGroupMemberInputMedia> {
    @InternalSerializationApi
    override val descriptor: SerialDescriptor = buildSerialDescriptor(MediaGroupMemberInputMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: MediaGroupMemberInputMedia) {
        when (value) {
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, value)
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, value)
            is InputMediaAudio -> InputMediaAudio.serializer().serialize(encoder, value)
            is InputMediaDocument -> InputMediaDocument.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): MediaGroupMemberInputMedia {
        val json = JsonObject.serializer().deserialize(decoder)

        return when (json[typeField] ?.jsonPrimitive ?.contentOrNull) {
            photoInputMediaType -> nonstrictJsonFormat.decodeFromJsonElement(InputMediaPhoto.serializer(), json)
            videoInputMediaType -> nonstrictJsonFormat.decodeFromJsonElement(InputMediaVideo.serializer(), json)
            audioInputMediaType -> nonstrictJsonFormat.decodeFromJsonElement(InputMediaAudio.serializer(), json)
            documentInputMediaType -> nonstrictJsonFormat.decodeFromJsonElement(InputMediaDocument.serializer(), json)
            else -> error("Illegal type of incoming MediaGroupMemberInputMedia")
        }
    }
}
