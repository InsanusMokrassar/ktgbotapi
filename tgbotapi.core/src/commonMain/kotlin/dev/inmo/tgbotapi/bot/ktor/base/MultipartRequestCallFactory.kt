package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.kslog.common.KSLog
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.mapWithCommonValues
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.*
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json

class MultipartRequestCallFactory(logger: KSLog? = null) : AbstractRequestCallFactory(logger ?: DefaultKTgBotAPIKSLog) {
    private val localSimpleRequestCallFactory = SimpleRequestCallFactory(logger)
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? {
        return when (request) {
            !is MultipartRequest -> null
            is MultipartRequest.Common -> localSimpleRequestCallFactory.makeCall(client, urlsKeeper, request.data, jsonFormatter)
            else -> super.makeCall(client, urlsKeeper, request, jsonFormatter)
        }
    }

    override fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any? = (request as? MultipartRequest) ?.let { castedRequest ->
        MultiPartFormDataContent(
            formData {
                val params = castedRequest.paramsJson.mapWithCommonValues() - castedRequest.mediaMap.keys
                for ((key, value) in castedRequest.mediaMap + params) {
                    when (value) {
                        is MultipartFile -> appendInput(
                            key,
                            Headers.build {
                                append(HttpHeaders.ContentDisposition, "filename=${value.filename}")
                            },
                            block = value::input
                        )
                        is FileId -> append(key, value.fileId)
                        else -> append(key, value.toString())
                    }
                }
            }
        )
    }
}
