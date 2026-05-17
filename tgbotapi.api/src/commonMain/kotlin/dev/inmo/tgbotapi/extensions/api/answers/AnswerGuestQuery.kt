package dev.inmo.tgbotapi.extensions.api.answers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.answers.AnswerGuestQuery
import dev.inmo.tgbotapi.types.GuestQueryId
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.guest.SentGuestMessage

public suspend fun TelegramBot.answerGuestQuery(
    guestQueryId: GuestQueryId,
    result: InlineQueryResult
): SentGuestMessage = execute(AnswerGuestQuery(guestQueryId, result))

public suspend fun TelegramBot.answer(
    guestQueryId: GuestQueryId,
    result: InlineQueryResult
): SentGuestMessage = execute(AnswerGuestQuery(guestQueryId, result))
