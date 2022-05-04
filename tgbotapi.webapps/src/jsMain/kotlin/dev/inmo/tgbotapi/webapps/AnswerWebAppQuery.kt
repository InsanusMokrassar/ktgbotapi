package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerWebAppQuery
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult

suspend fun TelegramBot.answerWebAppQuery(
    result: InlineQueryResult
) = webApp.initDataUnsafe.queryId ?.let {
    execute(AnswerWebAppQuery(it, result))
}
