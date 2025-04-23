package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.messageAutoDeleteTimeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val seconds24Hours: Seconds = 60 * 60 * 24
private const val seconds7Days: Seconds = seconds24Hours * 7

@Serializable
data class MessageAutoDeleteTimerChanged(
    @SerialName(messageAutoDeleteTimeField)
    val newAutoDeleteTime: Seconds, // TODO:: check that it is seconds
) : ChannelEvent, GroupEvent, SupergroupEvent, PrivateEvent

val MessageAutoDeleteTimerChanged.isOff
    get() = newAutoDeleteTime == 0

val MessageAutoDeleteTimerChanged.is24Hours
    get() = newAutoDeleteTime == seconds24Hours

val MessageAutoDeleteTimerChanged.is7Days
    get() = newAutoDeleteTime == seconds7Days
