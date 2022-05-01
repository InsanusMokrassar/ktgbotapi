package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.micro_utils.coroutines.safelyWithResult
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.exceptions.newRequestException
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.plugins.timeout
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlin.collections.set

var defaultUpdateTimeoutForZeroDelay = 1000L

abstract class AbstractRequestCallFactory : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? {
        val preparedBody = prepareCallBody(client, urlsKeeper, request) ?: return null

        client.post {
            url(
                methodsCache[request.method()] ?: "${urlsKeeper.commonAPIUrl}/${request.method()}".also {
                    methodsCache[request.method()] = it
                }
            )
            accept(ContentType.Application.Json)

            if (request is GetUpdates) {
                request.timeout?.times(1000L) ?.let { customTimeoutMillis ->
                    if (customTimeoutMillis > 0) {
                        timeout {
                            requestTimeoutMillis = customTimeoutMillis
                            socketTimeoutMillis = customTimeoutMillis
                        }
                    } else {
                        timeout {
                            requestTimeoutMillis = defaultUpdateTimeoutForZeroDelay
                            socketTimeoutMillis = defaultUpdateTimeoutForZeroDelay
                        }
                    }
                }
            }

            setBody(preparedBody)
        }.let { response ->
            val content = response.bodyAsText()
            val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)

            return safelyWithResult {
                (responseObject.result?.let {
                    jsonFormatter.decodeFromJsonElement(request.resultDeserializer, it)
                } ?: response.let {
                    throw newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                })
            }.getOrThrow()
        }
    }

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any?
}
