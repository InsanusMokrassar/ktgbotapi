package dev.inmo.tgbotapi.utils

import dev.inmo.kslog.common.KSLog
import dev.inmo.micro_utils.coroutines.launchLoggingDropExceptions
import dev.inmo.micro_utils.coroutines.subscribeLoggingDropExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

context(bot: TelegramBot)
fun CoroutineScope.launchWithBotLogger(
    errorMessageBuilder: CoroutineScope.(Throwable) -> Any = { "Something web wrong" },
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launchLoggingDropExceptions(
    errorMessageBuilder,
    bot.Log,
    context,
    start,
    block
)

context(bot: TelegramBot)
fun <T> Flow<T>.subscribeWithBotLogger(
    scope: CoroutineScope,
    errorMessageBuilder: T.(Throwable) -> Any = { "Something web wrong" },
    block: suspend (T) -> Unit
) = subscribeLoggingDropExceptions (
    scope,
    errorMessageBuilder,
    bot.Log,
    block
)
