package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.types.typeField
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObjectSerializer

@Serializer(MediaGroupMemberInputMedia::class)
internal object MediaGroupMemberInputMediaSerializer : KSerializer<MediaGroupMemberInputMedia> {
    override val descriptor: SerialDescriptor = SerialDescriptor(MediaGroupMemberInputMedia::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: MediaGroupMemberInputMedia) {
        when (value) {
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, value)
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): MediaGroupMemberInputMedia {
        val json = JsonObjectSerializer.deserialize(decoder)

        return when (json.getPrimitiveOrNull(typeField) ?.contentOrNull) {
            photoInputMediaType -> nonstrictJsonFormat.fromJson(InputMediaPhoto.serializer(), json)
            videoInputMediaType -> nonstrictJsonFormat.fromJson(InputMediaVideo.serializer(), json)
            else -> error("Illegal type of incoming MediaGroupMemberInputMedia")
        }
    }
}