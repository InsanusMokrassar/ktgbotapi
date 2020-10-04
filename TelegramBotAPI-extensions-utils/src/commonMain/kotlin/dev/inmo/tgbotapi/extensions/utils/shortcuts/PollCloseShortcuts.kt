package dev.inmo.tgbotapi.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.types.LongSeconds
import com.github.insanusmokrassar.TelegramBotAPI.types.Seconds
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.ApproximateScheduledCloseInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.ExactScheduledCloseInfo
import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan

fun closePollExactAt(
    dateTime: DateTime
) = ExactScheduledCloseInfo(
    dateTime
)

fun closePollExactAfter(
    seconds: LongSeconds
) = closePollExactAt(
    DateTime.now() + TimeSpan(seconds.toDouble() * 1000L)
)
fun closePollExactAfter(
    seconds: Seconds
) = closePollExactAfter(
    seconds.toLong()
)

fun closePollAfter(
    seconds: LongSeconds
) = ApproximateScheduledCloseInfo(
    TimeSpan(seconds.toDouble() * 1000L)
)

fun closePollAfter(
    seconds: Seconds
) = closePollAfter(seconds.toLong())
