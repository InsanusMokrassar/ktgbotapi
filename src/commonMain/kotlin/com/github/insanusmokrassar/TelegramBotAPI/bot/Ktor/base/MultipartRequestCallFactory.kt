package com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.base

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorCallFactory
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapWithCommonValues
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.call
import io.ktor.client.request.accept
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.url
import io.ktor.http.*

class MultipartRequestCallFactory : KtorCallFactory {
    override suspend fun <T: Any> prepareCall(
        client: HttpClient,
        baseUrl: String,
        request: Request<T>
    ): HttpClientCall? = (request as? MultipartRequest) ?.let {
        castedRequest ->
        client.call {
            url("$baseUrl/${castedRequest.method()}")
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            body = MultiPartFormDataContent(
                formData {
                    val params = castedRequest.paramsJson.mapWithCommonValues()
                    for ((key, value) in castedRequest.mediaMap + params) {
                        when (value) {
                            is MultipartFile -> append(
                                key,
                                value.file.asInput(),
                                Headers.build {
                                    append(HttpHeaders.ContentType, value.mimeType)
                                    append(HttpHeaders.ContentDisposition, "filename=${value.fileId}")
                                }
                            )
                            is FileId -> append(key, value.fileId)
                            else -> append(key, value.toString())
                        }
                    }
                }
            )
            build()
        }
    }
}