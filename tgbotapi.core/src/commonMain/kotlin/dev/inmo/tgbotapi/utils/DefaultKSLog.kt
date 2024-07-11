package dev.inmo.tgbotapi.utils

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.LogLevel
import dev.inmo.kslog.common.TagLogger

/**
 * Default tag for [DefaultKTgBotAPIKSLog]. You may change it and tag will be changed since the near logging
 */
var DefaultKTgBotAPIKSLogSystemTag: String = "KTgBot"
/**
 * Default realization of [KSLog] which will be used everywhere where there is no some custom variant of [KSLog]
 *
 * By default, uses [KSLog] factory with lambda and tag [DefaultKTgBotAPIKSLogSystemTag] (which in fact falling back to
 * [KSLog.default] with `KTgBot` default tag)
 */
var DefaultKTgBotAPIKSLog: KSLog = KSLog { level: LogLevel, tag: String?, message: Any, throwable: Throwable? ->
    TagLogger(DefaultKTgBotAPIKSLogSystemTag).performLog(level, tag, message, throwable)
}
