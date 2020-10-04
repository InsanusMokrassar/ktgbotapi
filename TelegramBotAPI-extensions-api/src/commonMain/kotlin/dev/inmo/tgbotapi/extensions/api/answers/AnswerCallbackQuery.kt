package dev.inmo.tgbotapi.extensions.api.answers

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.answers.AnswerCallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.CallbackQuery
import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQueryIdentifier

suspend fun TelegramBot.answerCallbackQuery(
    callbackQueryId: CallbackQueryIdentifier,
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
