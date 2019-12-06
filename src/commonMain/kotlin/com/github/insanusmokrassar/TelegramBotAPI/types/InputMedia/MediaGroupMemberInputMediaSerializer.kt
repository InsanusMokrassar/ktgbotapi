package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializer(MediaGroupMemberInputMedia::class)
object MediaGroupMemberInputMediaSerializer : KSerializer<MediaGroupMemberInputMedia> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName(MediaGroupMemberInputMedia::class.toString())
    override fun serialize(encoder: Encoder, obj: MediaGroupMemberInputMedia) {
        when (obj) {
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, obj)
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, obj)
        }
    }

    override fun deserialize(decoder: Decoder): MediaGroupMemberInputMedia {
        TODO("not implemented")
    }
}