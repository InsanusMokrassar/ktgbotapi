package dev.inmo.tgbotapi.types.message.content

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.giveaway.GiveawayInfo
import dev.inmo.tgbotapi.types.giveaway.ScheduledGiveaway
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledGiveawayContent(
    private val chat: Chat,
    private val messageId: MessageId,
    val giveaway: ScheduledGiveaway
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<out AccessibleMessage> {
        return ForwardMessage(
            chat.id,
            toChatId = chatId,
            messageId = messageId,
            threadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent
        )
    }
}