package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.answers.answer
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.guest.SentGuestMessage
import dev.inmo.tgbotapi.types.message.abstracts.GuestContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.GuestMessage

public suspend fun TelegramBot.reply(
    message: GuestMessage,
    queryResult: InlineQueryResult
): SentGuestMessage = answer(
    message.guestQueryId,
    queryResult
)