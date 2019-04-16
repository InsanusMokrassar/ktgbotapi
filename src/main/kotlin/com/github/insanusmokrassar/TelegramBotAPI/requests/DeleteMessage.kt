package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.MessageAction
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class DeleteMessage(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageIdentifier
) : SimpleRequest<Boolean>, MessageAction {
    override fun method(): String = "deleteMessage"

    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
