package dev.inmo.tgbotapi.bot.Ktor

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.Ktor.base.*
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.exceptions.newRequestException
import dev.inmo.tgbotapi.bot.ktor.KtorPipelineStepsHolder
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.utils.*
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

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
    crossinline builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = KtorRequestsExecutorBuilder(telegramAPIUrlsKeeper).apply(builder).build()

/**
 * Shortcut for [telegramBot]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    token: String,
    apiUrl: String = telegramBotAPIDefaultUrl,
    crossinline builder: KtorRequestsExecutorBuilder.() -> Unit = {}
): TelegramBot = telegramBot(TelegramAPIUrlsKeeper(token, apiUrl), builder)

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
    private val pipelineStepsHolder: KtorPipelineStepsHolder = TODO()
) : BaseRequestsExecutor(telegramAPIUrlsKeeper) {
    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            this + createTelegramBotDefaultKtorCallRequestsFactories()
        } else {
            this
        }
    }

    private val client = client.config {
        if (client.feature(HttpTimeout) == null) {
            install(HttpTimeout)
        }
    }

    override suspend fun <T : Any> execute(request: Request<T>): T {
        return safely(
            { e ->
                pipelineStepsHolder.onRequestException(request, e) ?.let { return@safely it }

                throw if (e is ClientRequestException) {
                    val content = e.response.readText()
                    val responseObject = jsonFormatter.decodeFromString(Response.serializer(), content)
                    newRequestException(
                        responseObject,
                        content,
                        "Can't get result object from $content"
                    )
                } else {
                    e
                }
            }
        ) {
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
        }
    }

    override fun close() {
        client.close()
    }
}
