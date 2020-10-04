package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.LiveLocation.EditInlineMessageLiveLocation
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.Location
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    latitude: Double,
    longitude: Double,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditInlineMessageLiveLocation(
        inlineMessageId, latitude, longitude, replyMarkup
    )
)
suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    location: Location,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, location.latitude, location.longitude, replyMarkup)
