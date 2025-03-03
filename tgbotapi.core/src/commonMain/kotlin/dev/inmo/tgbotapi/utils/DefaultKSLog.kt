package dev.inmo.tgbotapi.utils

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.LogLevel
import dev.inmo.kslog.common.MessageFilter
import dev.inmo.kslog.common.TagLogger
import dev.inmo.kslog.common.filter.filtered
import kotlinx.coroutines.CancellationException

/**
 * Default tag for [DefaultKTgBotAPIKSLog]. You may change it and tag will be changed since the near logging
 */
var DefaultKTgBotAPIKSLogSystemTag: String = "KTgBot"

private inline fun CreateDefaultKSLogger(
    crossinline loggerTagGetter: () -> String,
    dropCancellationExceptions: Boolean = true,
    noinline additionalLoggerMapper: (KSLog.() -> KSLog)? = null
): KSLog {
    val filter: MessageFilter? = if (dropCancellationExceptions) {
        { ll, message, e ->
            e !is CancellationException
        }
    } else {
        null
    }
    return KSLog { level: LogLevel, tag: String?, message: Any, throwable: Throwable? ->
        TagLogger(loggerTagGetter()).performLog(level, tag, message, throwable)
    }.let {
        if (filter == null) {
            additionalLoggerMapper ?.invoke(it) ?: it
        } else {
            val base = it.filtered(filter)
            additionalLoggerMapper ?.invoke(base) ?: base
        }
    }
}
/**
 * Default realization of [KSLog] which will be used everywhere where there is no some custom variant of [KSLog]
 *
 * By default, uses [KSLog] factory with lambda and tag [DefaultKTgBotAPIKSLogSystemTag] (which in fact falling back to
 * [KSLog.default] with `KTgBot` default tag)
 *
 * @see SetDefaultKTgBotAPIKSLog
 */
var DefaultKTgBotAPIKSLog: KSLog = CreateDefaultKSLogger({ DefaultKTgBotAPIKSLogSystemTag } )

/**
 * Setting [DefaultKTgBotAPIKSLog] with applying of [dropCancellationExceptions] and [additionalFilter] to it
 *
 * @param dropCancellationExceptions Will drap coroutines job [CancellationException]s
 * @param additionalLoggerMapper Receives [KSLog] to allow you to use extensions like [filtered]. Returned
 * [KSLog] will be used as the result [KSLog] in [DefaultKTgBotAPIKSLog]
 */
fun SetDefaultKTgBotAPIKSLog(
    dropCancellationExceptions: Boolean = true,
    additionalLoggerMapper: (KSLog.() -> KSLog)? = null
) {
    DefaultKTgBotAPIKSLog = CreateDefaultKSLogger({ DefaultKTgBotAPIKSLogSystemTag }, dropCancellationExceptions, additionalLoggerMapper)
}
