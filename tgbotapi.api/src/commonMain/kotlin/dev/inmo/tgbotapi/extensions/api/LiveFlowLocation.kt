package dev.inmo.tgbotapi.extensions.api

import dev.inmo.micro_utils.coroutines.LinkedSupervisorScope
import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.tgbotapi.abstracts.Headed
import dev.inmo.tgbotapi.abstracts.HorizontallyAccured
import dev.inmo.tgbotapi.abstracts.Locationed
import dev.inmo.tgbotapi.abstracts.ProximityAlertable
import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.edit
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.extensions.api.send.sendLiveLocation
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LiveLocationContent
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.math.ceil

@Serializable
public data class EditLiveLocationInfo(
    override val latitude: Double,
    override val longitude: Double,
    override val horizontalAccuracy: Meters? = null,
    override val heading: Degrees? = null,
    override val proximityAlertRadius: Meters? = null,
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : Locationed, HorizontallyAccured, ProximityAlertable, Headed, WithReplyMarkup

/**
 * Will [sendLiveLocation] with the first [EditLiveLocationInfo] data and update than. Each [liveTimeMillis] passing,
 * the message will be sent again and new edits will be applied to the new message
 */
public suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<EditLiveLocationInfo>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    sentMessageFlow: FlowCollector<ContentMessage<LiveLocationContent>>? = null,
) {
    var currentLiveLocationMessage: ContentMessage<LiveLocationContent>? = null
    val updateMessageJob =
        if (liveTimeMillis == indefiniteLivePeriodDelayMillis) { // do not launch refreshing of message for indefinite live locations
            null
        } else {
            val scope = currentCoroutineContext().LinkedSupervisorScope()
            scope.launchSafelyWithoutExceptions(start = CoroutineStart.LAZY) {
                while (scope.isActive) {
                    delay(liveTimeMillis)
                    // Remove previous location message info to resend live location message
                    currentLiveLocationMessage = null
                }
            }
        }
    locationsFlow.collect {
        val capturedLiveLocationMessage = currentLiveLocationMessage
        if (capturedLiveLocationMessage == null) {
            updateMessageJob ?.start()
            currentLiveLocationMessage =
                send(
                    chatId,
                    it.latitude,
                    it.longitude,
                    if (liveTimeMillis == indefiniteLivePeriodDelayMillis) {
                        LiveLocation.INDEFINITE_LIVE_PERIOD
                    } else {
                        ceil(liveTimeMillis.toDouble() / 1000).toInt()
                    },
                    it.horizontalAccuracy,
                    it.heading,
                    it.proximityAlertRadius,
                    threadId,
                    businessConnectionId,
                    disableNotification,
                    protectContent,
                    allowPaidBroadcast,
                    effectId,
                    replyParameters,
                    it.replyMarkup,
                ).also {
                    sentMessageFlow ?.emit(it)
                }
        } else {
            edit(
                message = capturedLiveLocationMessage,
                latitude = it.latitude,
                longitude = it.longitude,
                horizontalAccuracy = it.horizontalAccuracy,
                heading = it.heading,
                proximityAlertRadius = it.proximityAlertRadius,
                replyMarkup = it.replyMarkup,
            ).also {
                sentMessageFlow ?.emit(it)
            }
        }
    }
}

/**
 * Will apply [Flow.map] to the [locationsFlow] to create [EditLiveLocationInfo] and pass the result flow to the
 * [handleLiveLocation] with [Flow] typed by [EditLiveLocationInfo]
 */
@JvmName("handleLiveLocationWithLocation")
@JsName("handleLiveLocationWithLocation")
public suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<Location>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    sentMessageFlow: FlowCollector<ContentMessage<LiveLocationContent>>? = null,
) {
    handleLiveLocation(
        chatId = chatId,
        locationsFlow =
            locationsFlow.map {
                EditLiveLocationInfo(
                    it.latitude,
                    it.longitude,
                    it.horizontalAccuracy,
                    (it as? LiveLocation) ?.heading,
                    (it as? LiveLocation) ?.proximityAlertRadius,
                    (it as? WithReplyMarkup) ?.replyMarkup as? InlineKeyboardMarkup,
                )
            },
        liveTimeMillis = liveTimeMillis,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        sentMessageFlow = sentMessageFlow,
    )
}

/**
 * Will apply [Flow.map] to the [locationsFlow] to create [EditLiveLocationInfo] and pass the result flow to the
 * [handleLiveLocation] with [Flow] typed by [EditLiveLocationInfo]
 */
@JvmName("handleLiveLocationWithLatLong")
@JsName("handleLiveLocationWithLatLong")
public suspend fun TelegramBot.handleLiveLocation(
    chatId: ChatIdentifier,
    locationsFlow: Flow<Pair<Double, Double>>,
    liveTimeMillis: Long = defaultLivePeriodDelayMillis,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    sentMessageFlow: FlowCollector<ContentMessage<LiveLocationContent>>? = null,
) {
    handleLiveLocation(
        chatId = chatId,
        locationsFlow =
            locationsFlow.map { (lat, long) ->
                EditLiveLocationInfo(
                    lat,
                    long,
                )
            },
        liveTimeMillis = liveTimeMillis,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        sentMessageFlow = sentMessageFlow,
    )
}
