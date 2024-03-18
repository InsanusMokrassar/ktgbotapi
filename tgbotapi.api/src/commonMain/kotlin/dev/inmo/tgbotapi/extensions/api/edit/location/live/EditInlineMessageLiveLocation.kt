package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.EditInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation

suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageId,
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
    inlineMessageId: InlineMessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, location.latitude, location.longitude, location.horizontalAccuracy, location.heading, location.proximityAlertRadius, replyMarkup)
