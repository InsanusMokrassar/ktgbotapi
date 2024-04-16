package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.abstracts.types.WithOptionalBusinessConnectionId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chatField
import dev.inmo.tgbotapi.types.messageIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessMessagesDeleted(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(chatField)
    val chat: PreviewChat,
    @SerialName(messageIdField)
    val messageId: MessageId
) : WithBusinessConnectionId
