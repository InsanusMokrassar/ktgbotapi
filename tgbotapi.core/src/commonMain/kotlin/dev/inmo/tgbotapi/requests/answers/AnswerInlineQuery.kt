package dev.inmo.tgbotapi.requests.answers

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

@Serializable
data class AnswerInlineQuery(
    @SerialName(inlineQueryIdField)
    val inlineQueryID: InlineQueryIdentifier,
    @Serializable(InlineQueryAnswersResultsSerializer::class)
    @SerialName(resultsField)
    val results: List<InlineQueryResult> = emptyList(),
    @SerialName(cacheTimeField)
    val cachedTime: Int? = null,
    @SerialName(isPersonalField)
    val isPersonal: Boolean? = null,
    @SerialName(nextOffsetField)
    val nextOffset: String? = null,
    @SerialName(switchPmTextField)
    val switchPmText: String? = null,
    @SerialName(switchPmParameterField)
    val switchPmParameter: String? = null
) : SimpleRequest<Boolean> {
    override fun method(): String = "answerInlineQuery"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun InlineQuery.createAnswer(
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null
) = AnswerInlineQuery(
    id,
    results,
    cachedTime,
    isPersonal,
    nextOffset,
    switchPmText,
    switchPmParameter
)

@RiskFeature
object InlineQueryAnswersResultsSerializer : KSerializer<List<InlineQueryResult>> by ListSerializer(
    InlineQueryResultSerializer
)
