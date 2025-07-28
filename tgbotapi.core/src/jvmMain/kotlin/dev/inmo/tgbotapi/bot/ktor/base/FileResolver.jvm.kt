package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.common.MPPFile

internal actual fun resolveFile(filename: String): MPPFile? = runCatching {
    MPPFile(filename).takeIf { it.exists() && it.isFile }
}.getOrElse { null }
