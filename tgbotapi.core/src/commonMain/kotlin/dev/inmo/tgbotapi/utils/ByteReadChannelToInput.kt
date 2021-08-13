package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.Input

expect suspend fun ByteReadChannel.asInput(): Input
