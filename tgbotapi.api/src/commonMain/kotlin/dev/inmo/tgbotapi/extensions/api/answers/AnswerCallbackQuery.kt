package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.CallbackQueryId

public suspend fun TelegramBot.answerCallbackQuery(
    callbackQueryId: CallbackQueryId,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
): Boolean = execute(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cachedTimeSeconds))

public suspend fun TelegramBot.answerCallbackQuery(
    callbackQuery: CallbackQuery,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
): Boolean = answerCallbackQuery(callbackQuery.id, text, showAlert, url, cachedTimeSeconds)

public suspend fun TelegramBot.answer(
    callbackQuery: CallbackQuery,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
): Boolean = answerCallbackQuery(callbackQuery.id, text, showAlert, url, cachedTimeSeconds)
