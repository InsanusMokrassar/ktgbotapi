package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.LiveLocation.EditInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation

suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditInlineMessageLiveLocation(
        inlineMessageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup
    )
)
suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, location.latitude, location.longitude, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, replyMarkup)
