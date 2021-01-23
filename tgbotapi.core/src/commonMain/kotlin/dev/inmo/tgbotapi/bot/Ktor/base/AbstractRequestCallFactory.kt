package dev.inmo.tgbotapi.bot.Ktor.base

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.Ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.exceptions.newRequestException
import dev.inmo.tgbotapi.requests.GetUpdates
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.timeout
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlin.collections.set

abstract class AbstractRequestCallFactory : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? {
        val preparedBody = prepareCallBody(client, urlsKeeper, request) ?: return null

        client.post<HttpResponse> {
            url(
                methodsCache[request.method()] ?: "${urlsKeeper.commonAPIUrl}/${request.method()}".also {
                    methodsCache[request.method()] = it
                }
            )
            accept(ContentType.Application.Json)

            if (request is GetUpdates) {
                request.timeout?.times(1000L)?.let { customTimeoutMillis ->
                    if (customTimeoutMillis > 0) {
                        timeout {
                            requestTimeoutMillis = customTimeoutMillis
                            socketTimeoutMillis = customTimeoutMillis
                        }
                    }
                }
            }

            body = preparedBody
        }.let { response ->
            val content = response.receive<String>()
            val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)

            return safely {
                (responseObject.result?.let {
                    jsonFormatter.decodeFromJsonElement(request.resultDeserializer, it)
                } ?: response.let {
                    throw newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                })
            }
        }
    }

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any?
}