package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.coroutines.firstOf
import kotlinx.coroutines.CoroutineScope

suspend fun <T> CoroutineScope.firstOf(
    vararg deferreds: suspend () -> T
): T = firstOf {
    deferreds.forEach {
        add {
            it()
        }
    }
}
