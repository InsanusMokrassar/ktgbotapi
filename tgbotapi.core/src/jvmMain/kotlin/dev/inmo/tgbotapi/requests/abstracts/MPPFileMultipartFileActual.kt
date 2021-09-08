package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.micro_utils.common.MPPFile

actual suspend fun MPPFile.asMultipartFile(): MultipartFile = toInputFile()
