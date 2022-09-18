package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.exceptions.*
import dev.inmo.tgbotapi.bot.ktor.base.*
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

@RiskFeature
fun createTelegramBotDefaultKtorCallRequestsFactories() = listOf(
    SimpleRequestCallFactory(),
    MultipartRequestCallFactory(),
    DownloadFileRequestCallFactory,
    DownloadFileChannelRequestCallFactory
)

class KtorRequestsExecutor(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient = HttpClient(),
    callsFactories: List<KtorCallFactory> = emptyList(),
    excludeDefaultFactories: Boolean = false,
    private val requestsLimiter: RequestLimiter = ExceptionsOnlyLimiter(),
    private val jsonFormatter: Json = nonstrictJsonFormat,
    private val pipelineStepsHolder: KtorPipelineStepsHolder = KtorPipelineStepsHolder
) : BaseRequestsExecutor(telegramAPIUrlsKeeper) {
    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            this + createTelegramBotDefaultKtorCallRequestsFactories()
        } else {
            this
        }
    }

    private val client = client.config {
        if (client.pluginOrNull(HttpTimeout) == null) {
            install(HttpTimeout)
        }
    }

    override suspend fun <T : Any> execute(request: Request<T>): T {
        return runCatchingSafely {
            pipelineStepsHolder.onBeforeSearchCallFactory(request, callsFactories)
            requestsLimiter.limit {
                var result: T? = null
                lateinit var factoryHandledRequest: KtorCallFactory
                for (potentialFactory in callsFactories) {
                    pipelineStepsHolder.onBeforeCallFactoryMakeCall(request, potentialFactory)
                    result = potentialFactory.makeCall(
                        client,
                        telegramAPIUrlsKeeper,
                        request,
                        jsonFormatter
                    )
                    result = pipelineStepsHolder.onAfterCallFactoryMakeCall(result, request, potentialFactory)
                    if (result != null) {
                        factoryHandledRequest = potentialFactory
                        break
                    }
                }

                result ?.let {
                    pipelineStepsHolder.onRequestResultPresented(it, request, factoryHandledRequest, callsFactories)
                } ?: pipelineStepsHolder.onRequestResultAbsent(request, callsFactories) ?: error("Can't execute request: $request")
            }
        }.let {
            val result = it.exceptionOrNull() ?.let { e ->
                pipelineStepsHolder.onRequestException(request, e) ?.let { return@let it }

                when (e) {
                    is ClientRequestException -> {
                        val exceptionResult = runCatchingSafely {
                            val content = e.response.bodyAsText()
                            val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)
                            newRequestException(
                                responseObject,
                                content,
                                "Can't get result object from $content"
                            )
                        }
                        exceptionResult.exceptionOrNull() ?.let {
                            CommonBotException(cause = e)
                        } ?: exceptionResult.getOrThrow()
                    }
                    is BotException -> e
                    else -> CommonBotException(cause = e)
                }
            } ?.let { Result.failure(it) } ?: it
            pipelineStepsHolder.onRequestReturnResult(result, request, callsFactories)
        }
    }

    override fun close() {
        client.close()
    }
}

class KtorRequestsExecutorBuilder(
    var telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
) {
    var client: HttpClient = HttpClient()
    var callsFactories: List<KtorCallFactory> = emptyList()
    var excludeDefaultFactories: Boolean = false
    var requestsLimiter: RequestLimiter = ExceptionsOnlyLimiter()
    var jsonFormatter: Json = nonstrictJsonFormat

    fun build() = KtorRequestsExecutor(telegramAPIUrlsKeeper, client, callsFactories, excludeDefaultFactories, requestsLimiter, jsonFormatter)
}

inline fun telegramBot(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = KtorRequestsExecutorBuilder(telegramAPIUrlsKeeper).apply(builder).build()

/**
 * Shortcut for [telegramBot]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    testServer: Boolean = false,
    builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, testServer, apiUrl), builder)
