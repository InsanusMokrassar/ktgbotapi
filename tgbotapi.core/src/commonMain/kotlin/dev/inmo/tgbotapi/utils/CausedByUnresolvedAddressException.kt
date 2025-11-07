package dev.inmo.tgbotapi.utils

import io.ktor.util.network.UnresolvedAddressException

fun Throwable.causedUnresolvedAddressException(): UnresolvedAddressException? {
    return causedBy(UnresolvedAddressException::class)
}

fun Throwable.isCausedUnresolvedAddressException(): Boolean = causedUnresolvedAddressException() != null
