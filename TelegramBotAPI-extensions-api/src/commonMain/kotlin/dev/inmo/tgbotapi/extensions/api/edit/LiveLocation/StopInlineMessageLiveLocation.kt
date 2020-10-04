package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.StopInlineMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.stopLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    StopInlineMessageLiveLocation(
        inlineMessageId, replyMarkup
    )
)
