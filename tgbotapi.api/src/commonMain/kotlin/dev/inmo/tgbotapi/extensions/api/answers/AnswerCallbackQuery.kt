package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.CallbackQueryId

suspend fun TelegramBot.answerCallbackQuery(
    callbackQueryId: CallbackQueryId,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
) = execute(AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cachedTimeSeconds))

suspend fun TelegramBot.answerCallbackQuery(
    callbackQuery: CallbackQuery,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
) = answerCallbackQuery(callbackQuery.id, text, showAlert, url, cachedTimeSeconds)

suspend fun TelegramBot.answer(
    callbackQuery: CallbackQuery,
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
) = answerCallbackQuery(callbackQuery.id, text, showAlert, url, cachedTimeSeconds)
