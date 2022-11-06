package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.abstracts.types.OptionalChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.files.StickerSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

@Serializable
object GetForumTopicIconStickers : SimpleRequest<List<Sticker>> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    private val deserializer = ListSerializer(StickerSerializer)

    override fun method(): String = "getForumTopicIconStickers"

    override val resultDeserializer: DeserializationStrategy<List<Sticker>>
        get() = deserializer
}
