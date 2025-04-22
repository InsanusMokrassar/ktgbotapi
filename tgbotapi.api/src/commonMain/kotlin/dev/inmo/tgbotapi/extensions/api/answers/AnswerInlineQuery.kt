package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerInlineQuery
import dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueryId

public suspend fun TelegramBot.answerInlineQuery(
    inlineQueryID: InlineQueryId,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null,
): Boolean =
    execute(
        AnswerInlineQuery(inlineQueryID, results, cachedTime, isPersonal, nextOffset, button),
    )

public suspend fun TelegramBot.answerInlineQuery(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null,
): Boolean = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, button)

public suspend fun TelegramBot.answer(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null,
): Boolean = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, button)

public suspend fun TelegramBot.answerInlineQuery(
    inlineQueryID: InlineQueryId,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?,
): Boolean =
    execute(
        AnswerInlineQuery(inlineQueryID, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter),
    )

public suspend fun TelegramBot.answerInlineQuery(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?,
): Boolean = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter)

public suspend fun TelegramBot.answer(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?,
): Boolean = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter)
