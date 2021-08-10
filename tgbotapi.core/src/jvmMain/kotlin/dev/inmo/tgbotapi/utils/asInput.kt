package dev.inmo.tgbotapi.utils

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.jvm.javaio.toInputStream
import io.ktor.utils.io.streams.asInput
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

actual suspend fun ByteReadChannel.asInput(): Input = toInputStream(coroutineContext.job).asInput()
