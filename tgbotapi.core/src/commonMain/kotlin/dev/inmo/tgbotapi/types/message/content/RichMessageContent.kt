package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.rich.RichTextInfo
import kotlinx.serialization.Serializable

@Serializable
data class RichMessageContent(
    private val chat: Chat,
    private val messageId: MessageId,
    val richMessage: RichTextInfo
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
    ): Request<ChatContentMessage<RichMessageContent>> {
        @Suppress("UNCHECKED_CAST")
        return ForwardMessage(
            chat.id,
            toChatId = chatId,
            messageId = messageId,
            threadId = messageThreadId,
            directMessageThreadId = directMessageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent
        ) as Request<ChatContentMessage<RichMessageContent>>
    }
}
