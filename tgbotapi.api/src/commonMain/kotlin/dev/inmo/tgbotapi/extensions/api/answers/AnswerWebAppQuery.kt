package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerWebAppQuery
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.WebAppQueryId

suspend fun TelegramBot.answerWebAppQuery(
    webAppQueryId: WebAppQueryId,
    result: InlineQueryResult
) = execute(AnswerWebAppQuery(webAppQueryId, result))

suspend fun TelegramBot.answer(
    webAppQueryId: WebAppQueryId,
    result: InlineQueryResult
) = execute(AnswerWebAppQuery(webAppQueryId, result))
