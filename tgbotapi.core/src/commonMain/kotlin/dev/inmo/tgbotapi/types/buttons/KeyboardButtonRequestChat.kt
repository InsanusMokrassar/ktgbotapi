package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.botAdministratorRightsField
import dev.inmo.tgbotapi.types.botIsMemberField
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.chatHasUsernameField
import dev.inmo.tgbotapi.types.chatIsChannelField
import dev.inmo.tgbotapi.types.chatIsCreatedField
import dev.inmo.tgbotapi.types.chatIsForumField
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.requestIdField
import dev.inmo.tgbotapi.types.userAdministratorRightsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @see Channel
 * @see Group
 */
@Serializable
data class KeyboardButtonRequestChat(
    @SerialName(requestIdField)
    val requestId: RequestId,
    @SerialName(chatIsChannelField)
    val isChannel: Boolean? = null,
    @SerialName(chatIsForumField)
    val isForum: Boolean? = null,
    @SerialName(chatHasUsernameField)
    val isPublic: Boolean? = null,
    @SerialName(chatIsCreatedField)
    val isOwnedBy: Boolean? = null,
    @SerialName(userAdministratorRightsField)
    val userRightsInChat: ChatCommonAdministratorRights? = null,
    @SerialName(botAdministratorRightsField)
    val botRightsInChat: ChatCommonAdministratorRights? = null,
    @SerialName(botIsMemberField)
    val botIsMember: Boolean? = null
) {
    companion object {
        fun Channel(
            requestId: RequestId,
            isPublic: Boolean? = null,
            isOwnedBy: Boolean? = null,
            userRightsInChat: ChatCommonAdministratorRights? = null,
            botRightsInChat: ChatCommonAdministratorRights? = null,
            botIsMember: Boolean? = null
        ) = KeyboardButtonRequestChat(
            requestId = requestId,
            isChannel = true,
            isForum = null,
            isPublic = isPublic,
            isOwnedBy = isOwnedBy,
            userRightsInChat = userRightsInChat,
            botRightsInChat = botRightsInChat,
            botIsMember = botIsMember
        )

        fun Group(
            requestId: RequestId,
            isForum: Boolean? = null,
            isPublic: Boolean? = null,
            isOwnedBy: Boolean? = null,
            userRightsInChat: ChatCommonAdministratorRights? = null,
            botRightsInChat: ChatCommonAdministratorRights? = null,
            botIsMember: Boolean? = null
        ) = KeyboardButtonRequestChat(
            requestId = requestId,
            isChannel = false,
            isForum = isForum,
            isPublic = isPublic,
            isOwnedBy = isOwnedBy,
            userRightsInChat = userRightsInChat,
            botRightsInChat = botRightsInChat,
            botIsMember = botIsMember
        )
    }
}

