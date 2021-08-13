package dev.inmo.tgbotapi.utils

import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.Input

actual suspend fun ByteReadChannel.asInput(): Input = ByteReadPacket(toByteArray())
