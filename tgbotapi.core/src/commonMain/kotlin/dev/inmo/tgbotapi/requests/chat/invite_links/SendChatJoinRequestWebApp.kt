package dev.inmo.tgbotapi.requests.chat.invite_links

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatJoinRequestQueryId
import dev.inmo.tgbotapi.types.chatJoinRequestQueryIdField
import dev.inmo.tgbotapi.types.webAppUrlField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/**
 * Use this method to process a received chat join request query by showing a Mini App to the user before deciding the
 * outcome. Call [AnswerChatJoinRequestQuery] to resolve the join request query based on the user interaction with the
 * Mini App.
 *
 * @see <a href="https://core.telegram.org/bots/api#sendchatjoinrequestwebapp">sendChatJoinRequestWebApp</a>
 */
@Serializable
data class SendChatJoinRequestWebApp(
    @SerialName(chatJoinRequestQueryIdField)
    val chatJoinRequestQueryId: ChatJoinRequestQueryId,
    @SerialName(webAppUrlField)
    val webAppUrl: String
) : SimpleRequest<Unit> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    override fun method(): String = "sendChatJoinRequestWebApp"
}
