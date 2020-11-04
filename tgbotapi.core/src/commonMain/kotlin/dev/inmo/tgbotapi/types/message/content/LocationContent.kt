package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

data class LocationContent(
    val location: Location
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<LocationContent>> = when (location) {
        is StaticLocation -> SendStaticLocation(
            chatId,
            location.latitude,
            location.longitude,
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
        is LiveLocation -> SendLiveLocation(
            chatId,
            location.latitude,
            location.longitude,
            location.livePeriod,
            location.horizontalAccuracy,
            location.heading,
            location.proximityAlertRadius,
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    }
}