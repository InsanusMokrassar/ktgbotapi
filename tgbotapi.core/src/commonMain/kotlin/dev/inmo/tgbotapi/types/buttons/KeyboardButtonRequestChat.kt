package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.request.RequestId
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
    val botIsMember: Boolean? = null,
    @SerialName(requestTitleField)
    val requestTitle: Boolean? = null,
    @SerialName(requestUsernameField)
    val requestUsername: Boolean? = null,
    @SerialName(requestPhotoField)
    val requestPhoto: Boolean? = null,
) {
    companion object {
        fun Channel(
            requestId: RequestId,
            isPublic: Boolean? = null,
            isOwnedBy: Boolean? = null,
            userRightsInChat: ChatCommonAdministratorRights? = null,
            botRightsInChat: ChatCommonAdministratorRights? = null,
            botIsMember: Boolean? = null,
            requestTitle: Boolean? = null,
            requestUsername: Boolean? = null,
            requestPhoto: Boolean? = null,
        ) = KeyboardButtonRequestChat(
            requestId = requestId,
            isChannel = true,
            isForum = null,
            isPublic = isPublic,
            isOwnedBy = isOwnedBy,
            userRightsInChat = userRightsInChat,
            botRightsInChat = botRightsInChat,
            botIsMember = botIsMember,
            requestTitle = requestTitle,
            requestUsername = requestUsername,
            requestPhoto = requestPhoto,
        )

        fun Group(
            requestId: RequestId,
            isForum: Boolean? = null,
            isPublic: Boolean? = null,
            isOwnedBy: Boolean? = null,
            userRightsInChat: ChatCommonAdministratorRights? = null,
            botRightsInChat: ChatCommonAdministratorRights? = null,
            botIsMember: Boolean? = null,
            requestTitle: Boolean? = null,
            requestUsername: Boolean? = null,
            requestPhoto: Boolean? = null,
        ) = KeyboardButtonRequestChat(
            requestId = requestId,
            isChannel = false,
            isForum = isForum,
            isPublic = isPublic,
            isOwnedBy = isOwnedBy,
            userRightsInChat = userRightsInChat,
            botRightsInChat = botRightsInChat,
            botIsMember = botIsMember,
            requestTitle = requestTitle,
            requestUsername = requestUsername,
            requestPhoto = requestPhoto,
        )
    }
}
