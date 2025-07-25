package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.micro_utils.ktor.common.input
import io.ktor.utils.io.ByteReadChannel

internal fun byteReadChannel(file: MPPFile): ByteReadChannel {
    return ByteReadChannel(file.input())
}
internal expect fun resolveFile(filename: String): MPPFile?