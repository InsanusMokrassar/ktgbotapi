package com.github.insanusmokrassar.TelegramBotAPI.requests.chat

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class ExportChatInviteLink(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<String> {
    override fun method(): String = "exportChatInviteLink"
    override val resultDeserializer: DeserializationStrategy<String>
        get() = String.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
