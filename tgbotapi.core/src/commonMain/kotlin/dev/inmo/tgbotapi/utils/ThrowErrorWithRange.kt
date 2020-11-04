package dev.inmo.tgbotapi.utils

internal fun <T : Comparable<T>> throwRangeError(
    valueName: String,
    range: ClosedRange<T>,
    actualValue: T
): Nothing = error("$valueName must be in range $range, but was $actualValue")
