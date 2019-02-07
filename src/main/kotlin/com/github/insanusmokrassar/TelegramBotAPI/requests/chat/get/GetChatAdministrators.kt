package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.RawChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.chatIdField
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer

@Serializable
data class GetChatAdministrators(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier
): ChatRequest, SimpleRequest<List<RawChatMember>> {
    override fun method(): String = "getChatAdministrators"
    override fun resultSerializer(): KSerializer<List<RawChatMember>> = ArrayListSerializer(RawChatMember.serializer())
}
