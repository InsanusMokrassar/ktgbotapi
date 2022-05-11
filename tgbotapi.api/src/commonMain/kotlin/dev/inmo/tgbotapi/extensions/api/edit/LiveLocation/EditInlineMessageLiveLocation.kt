package dev.inmo.tgbotapi.extensions.api.edit.LiveLocation

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.requests.edit.location.live.EditInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation

@Deprecated("Replaced", ReplaceWith("editLiveLocation", "dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation"))
suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, latitude, longitude, horizontalAccuracy, heading, proximityAlertRadius, replyMarkup)
@Deprecated("Replaced", ReplaceWith("editLiveLocation", "dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation"))
suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageIdentifier,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null
) = editLiveLocation(inlineMessageId, location, replyMarkup)
