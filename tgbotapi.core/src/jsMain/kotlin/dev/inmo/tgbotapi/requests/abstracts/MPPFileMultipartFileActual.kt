package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.*
import io.ktor.utils.io.ByteReadChannel

actual suspend fun MPPFile.asMultipartFile(): MultipartFile = ByteReadChannel(bytes()).asMultipartFile(
    filename.name
)
