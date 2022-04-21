package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.micro_utils.common.filename
import dev.inmo.micro_utils.ktor.common.input

actual fun MPPFile.asMultipartFile(): MultipartFile = if (exists()) {
    MultipartFile(
        filename.string,
        ::input
    )
} else {
    error("Specified file $absolutePath does not exists")
}
