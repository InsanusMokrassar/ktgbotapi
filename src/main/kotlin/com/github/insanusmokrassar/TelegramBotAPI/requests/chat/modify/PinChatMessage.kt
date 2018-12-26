package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.DisableNotification
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class PinChatMessage (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageIdentifier,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false
): ChatRequest, SimpleRequest<Boolean>, DisableNotification {
    override fun method(): String = "pinChatMessage"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
