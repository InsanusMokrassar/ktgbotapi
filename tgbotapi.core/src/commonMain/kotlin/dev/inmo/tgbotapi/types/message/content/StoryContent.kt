package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage
import dev.inmo.tgbotapi.types.stories.Story
import kotlinx.serialization.Serializable

@Serializable
data class StoryContent(
    private val chat: Chat,
    private val messageId: MessageId,
    val story: Story
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
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<PossiblyForwardedMessage> {
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
