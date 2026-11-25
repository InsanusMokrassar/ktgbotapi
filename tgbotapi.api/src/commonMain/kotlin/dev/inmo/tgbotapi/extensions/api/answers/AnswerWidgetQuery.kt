package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerWidgetQuery
import dev.inmo.tgbotapi.types.widgets.WidgetQueryId
import dev.inmo.tgbotapi.types.widgets.WidgetQuerySource

public suspend fun TelegramBot.answerWidgetQuery(widgetQueryId: WidgetQueryId, data: String? = null): Unit =
    execute(AnswerWidgetQuery(widgetQueryId, data))

public suspend fun TelegramBot.answerWidgetQuery(query: WidgetQuerySource, data: String? = null): Unit =
    answerWidgetQuery(query.id, data)
