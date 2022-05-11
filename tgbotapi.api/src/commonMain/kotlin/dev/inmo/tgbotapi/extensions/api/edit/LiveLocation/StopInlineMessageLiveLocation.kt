package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.location.live.stopLiveLocation
import dev.inmo.tgbotapi.requests.edit.location.live.StopInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@Deprecated("Replaced", ReplaceWith("stopLiveLocation", "dev.inmo.tgbotapi.extensions.api.edit.location.live.stopLiveLocation"))
suspend fun TelegramBot.stopLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = stopLiveLocation(inlineMessageId, replyMarkup)
