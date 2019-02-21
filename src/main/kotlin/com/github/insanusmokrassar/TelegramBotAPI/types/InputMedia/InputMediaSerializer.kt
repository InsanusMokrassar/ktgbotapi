package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.*

@Serializer(InputMedia::class)
object InputMediaSerializer : KSerializer<InputMedia> {
    override fun serialize(encoder: Encoder, obj: InputMedia) {
        when (obj) {
            is InputMediaVideo -> InputMediaVideo.serializer().serialize(encoder, obj)
            is InputMediaAudio -> InputMediaAudio.serializer().serialize(encoder, obj)
            is InputMediaPhoto -> InputMediaPhoto.serializer().serialize(encoder, obj)
            is InputMediaAnimation -> InputMediaAnimation.serializer().serialize(encoder, obj)
            is InputMediaDocument -> InputMediaDocument.serializer().serialize(encoder, obj)
            else -> throw IllegalArgumentException("Can't perform and serialize $obj")
        }
    }

    override fun deserialize(decoder: Decoder): InputMedia {
        throw IllegalStateException("Object can't be deserialized")
    }
}
