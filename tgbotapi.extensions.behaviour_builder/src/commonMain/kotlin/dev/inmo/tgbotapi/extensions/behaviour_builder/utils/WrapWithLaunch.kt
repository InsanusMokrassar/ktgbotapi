package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import kotlinx.coroutines.CoroutineScope

internal fun <T> CoroutineScope.wrapWithLaunch(
    block: suspend (T) -> Unit
): suspend (T) -> Unit = {
    launchSafelyWithoutExceptions {
        block(it)
    }
}

internal fun <T> CoroutineScope.optionallyWrapWithLaunch(
    wrap: Boolean,
    block: suspend (T) -> Unit
): suspend (T) -> Unit = if (wrap) {
    wrapWithLaunch(block)
} else {
    block
}
