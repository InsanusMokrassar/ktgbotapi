package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.v
import dev.inmo.kslog.common.w
import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.exceptions.newRequestException
import dev.inmo.tgbotapi.requests.GetUpdatesRequest
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.types.message.textsources.pre
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.HttpClient
import io.ktor.client.plugins.timeout
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.collections.set

var defaultUpdateTimeoutForZeroDelay = 1000L

abstract class AbstractRequestCallFactory(
    protected open val logger: KSLog = DefaultKTgBotAPIKSLog
) : KtorCallFactory {
    private val methodsCache: MutableMap<String, String> = mutableMapOf()
    override suspend fun <T : Any> makeCall(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>,
        jsonFormatter: Json
    ): T? {
        val preparedBody = prepareCallBody(client, urlsKeeper, request) ?: return null
        logger.v {
            val bodyValue = if (preparedBody is TextContent) {
                preparedBody.text
            } else {
                preparedBody.toString()
            }
            "Prepared body for $request: $bodyValue"
        }

        client.post {
            url(
                methodsCache[request.method()] ?: "${urlsKeeper.commonAPIUrl}/${request.method()}".also {
                    methodsCache[request.method()] = it
                }
            )
            accept(ContentType.Application.Json)

            if (request is GetUpdatesRequest) {
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
            logger.v { "Raw answer for $request: $content" }
            val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)
            logger.v { "Answer as json for $request: $responseObject" }

            return runCatchingSafely {
                (responseObject.result?.let {
                    jsonFormatter.decodeFromJsonElement(request.resultDeserializer, it)
                } ?: response.let {
                    throw newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                })
            }.onFailure {
                logger.w { "Got exception answer for $request: $it" }
            }.getOrThrow()
        }
    }

    protected abstract fun <T : Any> prepareCallBody(
        client: HttpClient,
        urlsKeeper: TelegramAPIUrlsKeeper,
        request: Request<T>
    ): Any?
}
