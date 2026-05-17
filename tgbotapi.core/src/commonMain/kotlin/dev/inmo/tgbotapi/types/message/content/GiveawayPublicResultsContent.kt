package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.giveaway.GiveawayPublicResults
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class GiveawayPublicResultsContent(
    val giveaway: GiveawayPublicResults
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        directMessageThreadId: DirectMessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        suggestedPostParameters: SuggestedPostParameters?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ChatContentMessage<GiveawayPublicResultsContent>> {
        @Suppress("UNCHECKED_CAST")
        return ForwardMessage(
            giveaway.chat.id,
            toChatId = chatId,
            messageId = giveaway.messageId,
            threadId = messageThreadId,
            directMessageThreadId = directMessageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent
        ) as Request<ChatContentMessage<GiveawayPublicResultsContent>>
    }
}