package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class GetChatMembersCount(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<Int> {
    override fun method(): String = "getChatMembersCount"
    override val resultDeserializer: DeserializationStrategy<Int>
        get() = Int.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
