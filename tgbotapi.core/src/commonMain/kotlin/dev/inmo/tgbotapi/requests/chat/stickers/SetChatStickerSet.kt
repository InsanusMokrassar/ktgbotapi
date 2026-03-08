package dev.inmo.tgbotapi.requests.chat.stickers

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetChatStickerSet(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(stickerSetNameField)
    val stickerSetName: StickerSetName
): ChatRequest, SimpleRequest<Unit> {
    override fun method(): String = "setChatStickerSet"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
