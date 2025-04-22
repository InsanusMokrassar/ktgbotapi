package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.*

actual fun MPPFile.asMultipartFile(): MultipartFile =
    bytesSync().asMultipartFile(
        filename.name,
    )
