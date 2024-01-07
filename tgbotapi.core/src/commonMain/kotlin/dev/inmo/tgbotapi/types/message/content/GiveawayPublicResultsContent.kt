package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.giveaway.GiveawayPublicResults
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import kotlinx.serialization.Serializable

@Serializable
data class GiveawayPublicResultsContent(
    val giveaway: GiveawayPublicResults
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
        TODO("Not yet implemented")
    }
}