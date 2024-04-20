package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.kslog.common.*
import dev.inmo.micro_utils.coroutines.defaultSafelyExceptionHandler
import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.exceptions.BotException
import dev.inmo.tgbotapi.bot.exceptions.CommonBotException
import dev.inmo.tgbotapi.bot.exceptions.GetUpdatesConflict
import dev.inmo.tgbotapi.bot.exceptions.newRequestException
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.KtorPipelineStepsHolder
import dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutor
import dev.inmo.tgbotapi.bot.ktor.createTelegramBotDefaultKtorCallRequestsFactories
import dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class DefaultKtorRequestsExecutor internal constructor(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    client: HttpClient,
    callsFactories: List<KtorCallFactory>,
    excludeDefaultFactories: Boolean,
    private val requestsLimiter: RequestLimiter,
    private val jsonFormatter: Json,
    private val pipelineStepsHolder: KtorPipelineStepsHolder,
    private val logger: KSLog,
    diff: Unit
) : BaseRequestsExecutor(telegramAPIUrlsKeeper) {
    private val callsFactories: List<KtorCallFactory> = callsFactories.run {
        if (!excludeDefaultFactories) {
            logger.v { "Installing default factories" }
            this + createTelegramBotDefaultKtorCallRequestsFactories(logger)
        } else {
            logger.v { "Default factories will not be installed" }
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
            logger.v { "Start request $request" }
            pipelineStepsHolder.onBeforeSearchCallFactory(request, callsFactories)
            requestsLimiter.limit(request) {
                var result: T? = null
                lateinit var factoryHandledRequest: KtorCallFactory
                for (potentialFactory in callsFactories) {
                    pipelineStepsHolder.onBeforeCallFactoryMakeCall(request, potentialFactory)
                    logger.v { "Trying factory $potentialFactory for $request" }
                    val resultFromFactory = potentialFactory.makeCall(
                        client,
                        telegramAPIUrlsKeeper,
                        request,
                        jsonFormatter
                    )
                    logger.v { "Result of factory $potentialFactory handling $request: $resultFromFactory" }
                    result = pipelineStepsHolder.onAfterCallFactoryMakeCall(resultFromFactory, request, potentialFactory)
                    logger.v { "Result of pipeline $pipelineStepsHolder handling $resultFromFactory: $result" }
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
                logger.v(e) { "Got exception on handling of $request" }
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
                }.also { newException ->
                    logger.v(newException) { "Result exception on handling of $request is an exception: ${newException.stackTraceToString()}" }
                    if (newException is GetUpdatesConflict) {
                        logger.w(newException) {
                            "Warning!!! Other bot with the same bot token requests updates with getUpdate in parallel"
                        }
                    }
                }
            } ?.let { Result.failure(it) } ?: it
            pipelineStepsHolder.onRequestReturnResult(result, request, callsFactories).also {
                logger.v { "Result of handling $request: $it" }
            }
        }
    }

    override fun close() {
        client.close()
    }
}
