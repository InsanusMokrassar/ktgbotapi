package com.github.insanusmokrassar.TelegramBotAPI.requests.answers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.serializers.InlineQueryResultSerializer
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InlineQuery
import kotlinx.serialization.*
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class AnswerInlineQuery(
    @SerialName(inlineQueryIdField)
    val inlineQueryID: InlineQueryIdentifier,
    @Serializable(InlineQueryAnswersResultsSerializer::class)
    @SerialName(resultsField)
    val results: List<InlineQueryResult> = emptyList(),
    @SerialName(cachedTimeField)
    @Optional
    val cachedTime: Int? = null,
    @SerialName(isPersonalField)
    @Optional
    val isPersonal: Boolean? = null,
    @SerialName(nextOffsetField)
    @Optional
    val nextOffset: String? = null,
    @SerialName(switchPmTextField)
    @Optional
    val switchPmText: String? = null,
    @SerialName(switchPmParameterField)
    @Optional
    val switchPmParameter: String? = null
): SimpleRequest<Boolean> {
    override fun method(): String = "answerInlineQuery"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
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

object InlineQueryAnswersResultsSerializer: KSerializer<List<InlineQueryResult>> by ArrayListSerializer(
    InlineQueryResultSerializer
)
