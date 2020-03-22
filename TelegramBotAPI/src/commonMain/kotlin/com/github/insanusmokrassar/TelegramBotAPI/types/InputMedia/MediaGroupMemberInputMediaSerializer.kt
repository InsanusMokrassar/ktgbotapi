package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

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
        TODO("not implemented")
    }
}