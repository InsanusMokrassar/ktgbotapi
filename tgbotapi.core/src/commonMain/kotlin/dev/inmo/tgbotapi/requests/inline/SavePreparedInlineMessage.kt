package dev.inmo.tgbotapi.requests.inline

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import dev.inmo.tgbotapi.types.InlineQueries.prepared.PreparedInlineMessage
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class SavePreparedInlineMessage(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @Serializable(InlineQueryResultSerializer::class)
    @SerialName(resultField)
    val result: InlineQueryResult,
    @SerialName(allowUserChatsField)
    val allowSendToUsers: Boolean = false,
    @SerialName(allowBotChatsField)
    val allowSendToBots: Boolean = false,
    @SerialName(allowGroupChatsField)
    val allowSendToGroups: Boolean = false,
    @SerialName(allowChannelChatsField)
    val allowSendToChannels: Boolean = false,
) : SimpleRequest<PreparedInlineMessage> {
    override fun method(): String = "savePreparedInlineMessage"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<PreparedInlineMessage>
        get() = PreparedInlineMessage.serializer()
}
