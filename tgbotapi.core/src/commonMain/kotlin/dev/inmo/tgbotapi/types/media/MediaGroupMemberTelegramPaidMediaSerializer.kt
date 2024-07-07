package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

@RiskFeature
object MediaGroupMemberTelegramPaidMediaSerializer : KSerializer<MediaGroupMemberTelegramPaidMedia> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor(MediaGroupMemberTelegramMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: MediaGroupMemberTelegramPaidMedia) {
        when (value) {
            is TelegramPaidMediaPhoto -> TelegramPaidMediaPhoto.serializer().serialize(encoder, value)
            is TelegramPaidMediaVideo -> TelegramPaidMediaVideo.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): MediaGroupMemberTelegramPaidMedia {
        val json = JsonObject.serializer().deserialize(decoder)

        return when (json[typeField] ?.jsonPrimitive ?.contentOrNull) {
            photoTelegramPaidMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramPaidMediaPhoto.serializer(), json)
            videoTelegramPaidMediaType -> nonstrictJsonFormat.decodeFromJsonElement(TelegramPaidMediaVideo.serializer(), json)
            else -> error("Illegal type of incoming MediaGroupMemberTelegramMedia")
        }
    }
}