package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.*
import io.ktor.utils.io.core.*

actual suspend fun ByteReadChannel.asInput(): Input = ByteReadPacket(toByteArray())
