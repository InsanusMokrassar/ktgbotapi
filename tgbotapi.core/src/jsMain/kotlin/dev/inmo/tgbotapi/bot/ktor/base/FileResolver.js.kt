package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.common.MPPFile

internal actual fun resolveFile(filename: String): MPPFile? = null // on JS in common case there is no opportunity to take file based on its name