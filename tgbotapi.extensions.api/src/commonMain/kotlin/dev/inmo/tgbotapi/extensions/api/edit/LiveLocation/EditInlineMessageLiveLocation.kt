package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.LiveLocation.EditInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

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
    location: StaticLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, location.latitude, location.longitude, replyMarkup)
