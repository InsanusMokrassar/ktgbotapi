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
    @SerialName(rightsField)
    val rights: BusinessBotRights = BusinessBotRights(),
    @SerialName(isEnabledField)
    val isEnabled: Boolean,
) {
    val asBusinessConnection
        get() = when (isEnabled) {
            true ->
                BusinessConnection.Enabled(
                    id = id,
                    user = user,
                    userChatId = userChatId,
                    date = date,
                    rights = rights,
                )
            false ->
                BusinessConnection.Disabled(
                    id = id,
                    user = user,
                    userChatId = userChatId,
                    date = date,
                    rights = rights,
                )
        }

    constructor(businessConnection: BusinessConnection) : this(
        id = businessConnection.id,
        user = businessConnection.user,
        userChatId = businessConnection.userChatId,
        date = businessConnection.date,
        rights = businessConnection.rights,
        isEnabled = businessConnection.isEnabled,
    )
}
