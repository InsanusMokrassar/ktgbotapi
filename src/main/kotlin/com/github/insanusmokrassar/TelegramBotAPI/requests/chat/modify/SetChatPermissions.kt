package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatPermissions (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(permissionsField)
    val permissions: ChatPermissions
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "setChatPermissions"
    override fun resultDeserializer(): KSerializer<Boolean> = BooleanSerializer
}
