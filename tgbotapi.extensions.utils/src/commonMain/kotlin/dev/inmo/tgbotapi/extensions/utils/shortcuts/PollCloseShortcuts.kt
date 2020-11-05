package dev.inmo.tgbotapi.extensions.utils.shortcuts

import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import dev.inmo.tgbotapi.types.LongSeconds
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo

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
