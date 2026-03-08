package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

@Serializable
data class SetChatTitle (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(titleField)
    val title: String
): ChatRequest, SimpleRequest<Unit> {
    init {
        if (title.length !in chatTitleLength) {
            DefaultKTgBotAPIKSLog.w("SetChatTitle", "Chat title must be in $chatTitleLength range")
        }
    }

    override fun method(): String = "setChatTitle"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
