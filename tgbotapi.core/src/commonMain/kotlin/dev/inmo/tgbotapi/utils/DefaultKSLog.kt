package dev.inmo.tgbotapi.utils

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.TagLogger

/**
 * Default realization of [KSLog] which will be used everywhere where there is no some custom variant of [KSLog]
 *
 * By default, uses [TagLogger] with tag `KTgBot` (which in fact falling back to [KSLog.default] with `KTgBot` default tag)
 */
var DefaultKTgBotAPIKSLog: KSLog = TagLogger("KTgBot")
