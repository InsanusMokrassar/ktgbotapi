package dev.inmo.tgbotapi.extensions.api

import dev.inmo.micro_utils.coroutines.LinkedSupervisorJob
import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.location.live.editLiveLocation
import dev.inmo.tgbotapi.extensions.api.send.sendLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LocationContent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.math.ceil

@Serializable
data class EditLiveLocationInfo(
    override val latitude: Double,
    override val longitude: Double,
    override val horizontalAccuracy: Meters? = null,
    override val heading: Degrees? = null,
    override val proximityAlertRadius: Meters? = null,
    override val replyMarkup: InlineKeyboardMarkup? = null
) : Locationed, HorizontallyAccured, ProximityAlertable, Headed, WithReplyMarkup

/**
 * Will [sendLiveLocation] with the first [EditLiveLocationInfo] data and update than. Each [liveTimeMillis] passing,
 * the message will be sent again and new edits will be applied to the new message
 */
suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    var currentLiveLocationMessage: ContentMessage<LocationContent>? = null
    val updateMessageJob = CoroutineScope(currentCoroutineContext().LinkedSupervisorJob()).launchSafelyWithoutExceptions(start = CoroutineStart.LAZY) {
        while (isActive) {
            delay(liveTimeMillis)
            // Remove previous location message info to resend live location message
            currentLiveLocationMessage = null
        }
    }
    locationsFlow.collect {
        val capturedLiveLocationMessage = currentLiveLocationMessage
        if (capturedLiveLocationMessage == null) {
            updateMessageJob.start()
            currentLiveLocationMessage = sendLiveLocation(
                chatId,
                it.latitude,
                it.longitude,
                ceil(liveTimeMillis.toDouble() / 1000).toInt(),
                it.horizontalAccuracy,
                it.heading,
                it.proximityAlertRadius,
                disableNotification,
                protectContent,
                replyToMessageId,
                allowSendingWithoutReply,
                it.replyMarkup
            )
        } else {
            editLiveLocation(
                capturedLiveLocationMessage,
                it.latitude,
                it.longitude,
                it.horizontalAccuracy,
                it.heading,
                it.proximityAlertRadius,
                it.replyMarkup
            )
        }
    }
}

/**
 * Will apply [Flow.map] to the [locationsFlow] to create [EditLiveLocationInfo] and pass the result flow to the
 * [handleLiveLocation] with [Flow] typed by [EditLiveLocationInfo]
 */
@JvmName("handleLiveLocationWithLocation")
@JsName("handleLiveLocationWithLocation")
suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        chatId,
        locationsFlow.map {
            EditLiveLocationInfo(
                it.latitude,
                it.longitude,
                it.horizontalAccuracy,
                (it as? LiveLocation) ?.heading,
                (it as? LiveLocation) ?.proximityAlertRadius,
                (it as? WithReplyMarkup) ?.replyMarkup as? InlineKeyboardMarkup
            )
        },
        liveTimeMillis,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )
}

/**
 * Will apply [Flow.map] to the [locationsFlow] to create [EditLiveLocationInfo] and pass the result flow to the
 * [handleLiveLocation] with [Flow] typed by [EditLiveLocationInfo]
 */
@JvmName("handleLiveLocationWithLatLong")
@JsName("handleLiveLocationWithLatLong")
suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) {
    handleLiveLocation(
        chatId,
        locationsFlow.map { (lat, long) ->
            EditLiveLocationInfo(
                lat,
                long
            )
        },
        liveTimeMillis,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )
}
