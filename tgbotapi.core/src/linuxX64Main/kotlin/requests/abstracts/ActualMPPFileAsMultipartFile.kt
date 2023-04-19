package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.micro_utils.ktor.common.input
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile

actual fun MPPFile.asMultipartFile(): MultipartFile = MultipartFile(this.name) {
    input()
}
