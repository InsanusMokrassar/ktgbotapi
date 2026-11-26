package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest

internal fun <T : Any> SimpleRequest<T>.withMultipartFiles(vararg inputs: InputFile?): Request<T> {
    val files = inputs.filterIsInstance<MultipartFile>()
    return if (files.isEmpty()) this else CommonMultipartFileRequest(this, files.associateBy { it.fileId })
}
