package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UsernameAtLessSerializer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The bot's own identity and capabilities returned by getMe. */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class BotFullInfo(
    @SerialName(idField)
    val id: UserId,
    @SerialName(firstNameField)
    val firstName: String,
    @SerialName(usernameField)
    val username: @Serializable(UsernameAtLessSerializer::class) Username? = null,
    @SerialName(activeUsernamesField)
    val activeUsernames: List<@Serializable(UsernameAtLessSerializer::class) Username> = emptyList(),
    @SerialName(canJoinGroupsField)
    val canJoinGroups: Boolean = false,
    @SerialName(canReadAllGroupMessagesField)
    val canReadAllGroupMessages: Boolean = false,
    @SerialName(supportsGuestQueriesField)
    val supportsGuestQueries: Boolean = false,
    @SerialName(supportInlineQueriesField)
    val supportsInlineQueries: Boolean = false,
    @SerialName(inlineQueryPlaceholderField)
    val inlineQueryPlaceholder: String? = null,
    @SerialName(needsInlineQueryLocationField)
    val needsInlineQueryLocation: Boolean = false,
    @SerialName(canConnectToBusinessField)
    val canConnectToBusiness: Boolean = false,
    @SerialName(hasMainWebAppField)
    val hasMainWebApp: Boolean = false,
    @SerialName(hasTopicsEnabledField)
    val hasTopicsEnabled: Boolean = false,
    @SerialName(allowUsersToCreateTopicsField)
    val allowsUsersToCreateTopics: Boolean = false,
    @SerialName(hasTopicSidebarField)
    val hasTopicSidebar: Boolean = false,
    @SerialName(canManageBotsField)
    val canManageBots: Boolean = false,
    @SerialName(supportsJoinRequestQueriesField)
    val supportsJoinRequestQueries: Boolean = false
) {
    @EncodeDefault
    @SerialName(isBotField)
    val isBot: Boolean = true
}
