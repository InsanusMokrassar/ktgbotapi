package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.abstracts.WithPreviewChatAndMessageId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.reactions.ReactionsCount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageReactionsCountUpdated(
    @SerialName(chatField)
    override val chat: PreviewChat,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @Serializable(TelegramDateSerializer::class)
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(reactionsField)
    val reactions: List<ReactionsCount>,
) : WithPreviewChatAndMessageId
