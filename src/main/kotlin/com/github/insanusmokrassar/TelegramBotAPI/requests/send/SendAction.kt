package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.actions.BotAction
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendChatMessageRequest
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

/**
 * Send notification to user which will be shown for 5 seconds or while user have no messages from bot
 */
@Serializable
data class SendAction(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(actionField)
    val action: BotAction
): SendChatMessageRequest<Boolean> {
    override fun method(): String = "sendChatAction"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
