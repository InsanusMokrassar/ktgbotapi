package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class DeleteMessage(
    @SerialName(chatIdField)
    val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    val messageId: MessageIdentifier
) : SimpleRequest<Boolean> {
    override fun method(): String = "deleteMessage"

    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
