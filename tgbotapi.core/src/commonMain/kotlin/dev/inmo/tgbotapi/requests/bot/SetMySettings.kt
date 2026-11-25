package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/** Changes only the supplied settings. An empty [inlineQueryPlaceholder] restores Telegram's default. */
@Serializable
data class SetMySettings(
    @SerialName(supportInlineQueriesField)
    val supportsInlineQueries: Boolean? = null,
    @SerialName(inlineQueryPlaceholderField)
    val inlineQueryPlaceholder: String? = null,
    @SerialName(needsInlineQueryLocationField)
    val needsInlineQueryLocation: Boolean? = null,
    @SerialName(inlineFeedbackShareDenominatorField)
    val inlineFeedbackShareDenominator: InlineFeedbackShareDenominator? = null,
    @SerialName(canJoinGroupsField)
    val canJoinGroups: Boolean? = null,
    @SerialName(canReadAllGroupMessagesField)
    val canReadAllGroupMessages: Boolean? = null,
    @SerialName(canConnectToBusinessField)
    val canConnectToBusiness: Boolean? = null,
    @SerialName(canManageBotsField)
    val canManageBots: Boolean? = null,
    @SerialName(supportsGuestQueriesField)
    val supportsGuestQueries: Boolean? = null,
    @SerialName(supportsJoinRequestQueriesField)
    val supportsJoinRequestQueries: Boolean? = null,
    @SerialName(hasTopicsEnabledField)
    val hasTopicsEnabled: Boolean? = null,
    @SerialName(allowUsersToCreateTopicsField)
    val allowsUsersToCreateTopics: Boolean? = null,
    @SerialName(hasTopicSidebarField)
    val hasTopicSidebar: Boolean? = null,
    @SerialName(canCommunicateWithBotsField)
    val canCommunicateWithBots: Boolean? = null,
    @SerialName(callbackQueryModeField)
    val callbackQueryMode: CallbackQueryMode? = null
) : SimpleRequest<Unit> {
    override fun method(): String = "setMySettings"
    override val resultDeserializer: DeserializationStrategy<Unit> get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
}
