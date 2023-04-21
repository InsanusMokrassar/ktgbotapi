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
    @SerialName(buttonField)
    val button: InlineQueryResultsButton? = null,
) : SimpleRequest<Boolean> {
    constructor(
        inlineQueryID: InlineQueryIdentifier,
        results: List<InlineQueryResult> = emptyList(),
        cachedTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        switchPmText: String?,
        switchPmParameter: String?
    ) : this(
        inlineQueryID,
        results,
        cachedTime,
        isPersonal,
        nextOffset,
        switchPmText ?.let {
            switchPmParameter ?.let {
                InlineQueryResultsButton.Start(switchPmText, switchPmParameter)
            }
        }
    )

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
    button: InlineQueryResultsButton? = null,
) = AnswerInlineQuery(
    id,
    results,
    cachedTime,
    isPersonal,
    nextOffset,
    button
)

fun InlineQuery.createAnswer(
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?
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
