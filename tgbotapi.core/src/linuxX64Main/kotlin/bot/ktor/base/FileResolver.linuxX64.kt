package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.common.MPPFile
import okio.FileSystem
import okio.Path

internal actual fun resolveFile(filename: String): MPPFile? = runCatching {
    with(Path) { filename.toPath() }.takeIf {
        FileSystem.SYSTEM.exists(it) && FileSystem.SYSTEM.metadata(it).isRegularFile
    }
}.getOrElse { null }