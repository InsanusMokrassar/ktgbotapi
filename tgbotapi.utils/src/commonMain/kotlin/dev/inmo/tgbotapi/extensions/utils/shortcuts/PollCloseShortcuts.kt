package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.tgbotapi.types.LongSeconds
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.polls.ApproximateScheduledCloseInfo
import dev.inmo.tgbotapi.types.polls.ExactScheduledCloseInfo
import korlibs.time.DateTime
import korlibs.time.TimeSpan

fun closePollExactAt(dateTime: DateTime) =
    ExactScheduledCloseInfo(
        dateTime,
    )

fun closePollExactAfter(seconds: LongSeconds) =
    closePollExactAt(
        DateTime.now() + TimeSpan(seconds.toDouble() * 1000L),
    )

fun closePollExactAfter(seconds: Seconds) =
    closePollExactAfter(
        seconds.toLong(),
    )

fun closePollAfter(seconds: LongSeconds) =
    ApproximateScheduledCloseInfo(
        TimeSpan(seconds.toDouble() * 1000L),
    )

fun closePollAfter(seconds: Seconds) = closePollAfter(seconds.toLong())
