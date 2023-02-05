package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.botAdministratorRightsField
import dev.inmo.tgbotapi.types.botIsMemberField
import dev.inmo.tgbotapi.types.chat.member.ChatAdministratorRights
import dev.inmo.tgbotapi.types.chatHasUsernameField
import dev.inmo.tgbotapi.types.chatIsChannelField
import dev.inmo.tgbotapi.types.chatIsCreatedField
import dev.inmo.tgbotapi.types.chatIsForumField
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.types.requestIdField
import dev.inmo.tgbotapi.types.userAdministratorRightsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val userRightsInChat: ChatAdministratorRights? = null,
    @SerialName(botAdministratorRightsField)
    val botRightsInChat: ChatAdministratorRights? = null,
    @SerialName(botIsMemberField)
    val botIsMember: Boolean? = null
)

