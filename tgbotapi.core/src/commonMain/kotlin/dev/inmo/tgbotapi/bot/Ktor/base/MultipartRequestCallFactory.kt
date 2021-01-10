package dev.inmo.tgbotapi.bot.Ktor.base

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.mapWithCommonValues
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders

object MultipartRequestCallFactory : AbstractRequestCallFactory() {
    override fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
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
                            },
                            block = value.file::input
                        )
                        is FileId -> append(key, value.fileId)
                        else -> append(key, value.toString())
                    }
                }
            }
        )
    }
}