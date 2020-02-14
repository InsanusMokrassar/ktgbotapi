package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapWithCommonValues
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders

class MultipartRequestCallFactory : AbstractRequestCallFactory() {

    override fun <T : Any> prepareCallBody(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): Any? = (request as? MultipartRequest) ?.let { castedRequest ->
        MultiPartFormDataContent(
            formData {
                val params = castedRequest.paramsJson.mapWithCommonValues()
                for ((key, value) in castedRequest.mediaMap + params) {
                    when (value) {
                        is MultipartFile -> appendInput(
                            key,
                            Headers.build {
                                append(HttpHeaders.ContentType, value.mimeType)
                                append(HttpHeaders.ContentDisposition, "filename=${value.fileId}")
                            }
                        ) {
                            value.file.asInput()
                        }
                        is FileId -> append(key, value.fileId)
                        else -> append(key, value.toString())
                    }
                }
            }
        )
    }
}