package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawBusinessConnection(
    @SerialName(idField)
    val id: BusinessConnectionId,
    @SerialName(userField)
    val user: PreviewUser,
    @SerialName(userChatIdField)
    val userChatId: ChatId,
    @SerialName(dateField)
    val date: TelegramDate,
    @SerialName(canReplyField)
    val canReply: Boolean,
    @SerialName(isEnabledField)
    val isEnabled: Boolean
) {
    val asBusinessConnection
        get() = when (isEnabled) {
            true -> BusinessConnection.Enabled(
                id = id,
                user = user,
                userChatId = userChatId,
                date = date,
                canReply = canReply
            )
            false -> BusinessConnection.Disabled(
                id = id,
                user = user,
                userChatId = userChatId,
                date = date,
                canReply = canReply
            )
        }

    constructor(businessConnection: BusinessConnection) : this(
        id = businessConnection.id,
        user = businessConnection.user,
        userChatId = businessConnection.userChatId,
        date = businessConnection.date,
        canReply = businessConnection.canReply,
        isEnabled = businessConnection.isEnabled,
    )
}
