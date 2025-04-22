package dev.inmo.tgbotapi.extensions.api.edit.location.live

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.location.live.EditInlineMessageLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation

public suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageId,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Meters? = null,
    heading: Degrees? = null,
    proximityAlertRadius: Meters? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean =
    execute(
        EditInlineMessageLiveLocation(
            inlineMessageId,
            latitude,
            longitude,
            horizontalAccuracy,
            heading,
            proximityAlertRadius,
            replyMarkup,
        ),
    )

public suspend fun TelegramBot.editLiveLocation(
    inlineMessageId: InlineMessageId,
    location: LiveLocation,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean =
    editLiveLocation(
        inlineMessageId,
        location.latitude,
        location.longitude,
        location.horizontalAccuracy,
        location.heading,
        location.proximityAlertRadius,
        replyMarkup,
    )
