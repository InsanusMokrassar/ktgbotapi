package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.*
import io.ktor.utils.io.ByteReadChannel

actual fun MPPFile.asMultipartFile(): MultipartFile = bytesSync().asMultipartFile(
    filename.name
)
