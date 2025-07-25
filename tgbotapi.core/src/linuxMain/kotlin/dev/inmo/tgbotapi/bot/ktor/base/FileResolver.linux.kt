package dev.inmo.tgbotapi.bot.ktor.base

import okio.FileSystem
import okio.Path

internal actual fun resolveFile(filename: String): dev.inmo.micro_utils.common.MPPFile? = runCatching {
    with(Path) { filename.toPath() }.takeIf {
        FileSystem.SYSTEM.exists(it) && FileSystem.SYSTEM.metadata(it).isRegularFile
    }
}.getOrElse { null }