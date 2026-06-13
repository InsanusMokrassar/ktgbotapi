package dev.inmo.tgbotapi.types.managed_bots

import dev.inmo.tgbotapi.types.addedUsersField
import dev.inmo.tgbotapi.types.isAccessRestrictedField
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotAccessSettings(
    @SerialName(addedUsersField)
    val addedUsers: List<User>? = null
) {
    @EncodeDefault
    @SerialName(isAccessRestrictedField)
    val isAccessRestricted: Boolean = addedUsers != null
}
