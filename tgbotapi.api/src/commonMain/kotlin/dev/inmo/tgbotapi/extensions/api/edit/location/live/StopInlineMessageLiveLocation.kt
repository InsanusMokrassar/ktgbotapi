package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.StopInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.stopLiveLocation(
    inlineMessageId: InlineMessageId,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopInlineMessageLiveLocation(
        inlineMessageId, replyMarkup
    )
)
