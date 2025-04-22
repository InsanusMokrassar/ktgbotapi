package dev.inmo.tgbotapi.requests.answers

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.webapps.query.SentWebAppMessage
import kotlinx.serialization.*

/**
 * @param webAppQueryId [dev.inmo.tgbotapi.webapps.WebAppInitData.queryId]
 */
@Serializable
data class AnswerWebAppQuery(
    @SerialName(webAppQueryIdField)
    val webAppQueryId: WebAppQueryId,
    @SerialName(resultField)
    val result: InlineQueryResult,
) : SimpleRequest<SentWebAppMessage> {
    override fun method(): String = "answerWebAppQuery"

    override val resultDeserializer: DeserializationStrategy<SentWebAppMessage>
        get() = SentWebAppMessage.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
