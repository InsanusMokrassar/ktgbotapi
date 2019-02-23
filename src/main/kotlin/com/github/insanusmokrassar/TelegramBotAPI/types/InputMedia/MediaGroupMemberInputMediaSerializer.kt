package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.*

@Serializer(MediaGroupMemberInputMedia::class)
object MediaGroupMemberInputMediaSerializer : KSerializer<MediaGroupMemberInputMedia> {
    override fun serialize(encoder: Encoder, obj: MediaGroupMemberInputMedia) {
        when (obj) {
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, obj)
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, obj)
        }
    }
}