package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.readRemaining

actual suspend fun ByteReadChannel.asInput(): Input = readRemaining()
