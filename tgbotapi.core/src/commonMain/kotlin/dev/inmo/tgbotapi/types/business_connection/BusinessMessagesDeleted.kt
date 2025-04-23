package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessMessagesDeleted(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(chatField)
    val chat: PreviewChat,
    @SerialName(messageIdsField)
    val messageIds: List<MessageId>,
) : WithBusinessConnectionId
