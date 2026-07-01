package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.ForwardMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.CopyMessage
import dev.inmo.tgbotapi.requests.send.SendRichMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.rich.InputRichMessageMarkdown
import dev.inmo.tgbotapi.types.rich.RichBlock
import dev.inmo.tgbotapi.types.rich.RichBlockAudio
import dev.inmo.tgbotapi.types.rich.RichBlockMedia
import dev.inmo.tgbotapi.types.rich.RichBlockPhoto
import dev.inmo.tgbotapi.types.rich.RichBlockVideo
import dev.inmo.tgbotapi.types.rich.RichTextInfo
import dev.inmo.tgbotapi.types.rich.markdown
import dev.inmo.tgbotapi.types.rich.search
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
        val isThereMedia = richMessage.blocks.any {
            it.search {
                this is RichBlockMedia
            } != null
        }
        return if (isThereMedia) {
            @Suppress("UNCHECKED_CAST")
            ForwardMessage(
                chat.id,
                toChatId = chatId,
                messageId = messageId,
                threadId = messageThreadId,
                directMessageThreadId = directMessageThreadId,
                disableNotification = disableNotification,
                protectContent = protectContent,
                effectId = effectId,
                suggestedPostParameters = suggestedPostParameters,
            ) as Request<ChatContentMessage<RichMessageContent>>
        } else {
            SendRichMessage(
                chatId = chatId,
                richMessage = InputRichMessageMarkdown(richMessage.markdown, isRtl = richMessage.isRtl),
                threadId = messageThreadId,
                directMessageThreadId = directMessageThreadId,
                businessConnectionId = businessConnectionId,
                disableNotification = disableNotification,
                protectContent = protectContent,
                allowPaidBroadcast = allowPaidBroadcast,
                effectId = effectId,
                suggestedPostParameters = suggestedPostParameters,
                replyParameters = replyParameters,
                replyMarkup = replyMarkup
            )
        }
    }
}
