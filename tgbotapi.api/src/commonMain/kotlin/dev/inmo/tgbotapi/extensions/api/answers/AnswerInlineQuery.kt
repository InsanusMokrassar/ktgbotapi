package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerInlineQuery
import dev.inmo.tgbotapi.requests.answers.InlineQueryResultsButton
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueryIdentifier

suspend fun TelegramBot.answerInlineQuery(
    inlineQueryID: InlineQueryIdentifier,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null
) = execute(
    AnswerInlineQuery(inlineQueryID, results, cachedTime, isPersonal, nextOffset, button)
)

suspend fun TelegramBot.answerInlineQuery(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null
) = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, button)

suspend fun TelegramBot.answer(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    button: InlineQueryResultsButton? = null
) = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, button)

suspend fun TelegramBot.answerInlineQuery(
    inlineQueryID: InlineQueryIdentifier,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?
) = execute(
    AnswerInlineQuery(inlineQueryID, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter)
)

suspend fun TelegramBot.answerInlineQuery(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?
) = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter)

suspend fun TelegramBot.answer(
    inlineQuery: InlineQuery,
    results: List<InlineQueryResult> = emptyList(),
    cachedTime: Int? = null,
    isPersonal: Boolean? = null,
    nextOffset: String? = null,
    switchPmText: String?,
    switchPmParameter: String?
) = answerInlineQuery(inlineQuery.id, results, cachedTime, isPersonal, nextOffset, switchPmText, switchPmParameter)
