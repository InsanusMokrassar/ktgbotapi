package dev.inmo.tgbotapi.bot.ktor.base

import dev.inmo.kslog.common.KSLog
import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.bot.BaseRequestsExecutor
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

/**
 * This function is used in default constructor of [MultipleClientKtorRequestsExecutor] and on all non-native
 * platforms and MingwX64 should return [client]
 *
 * On LinuxX64 it will create copy with Curl engine or throw an exception if engine is different with Curl
 *
 * @throws IllegalArgumentException When pass non Curl-based [HttpClient] on LinuxX64
 */
internal expect inline fun platformClientCopy(client: HttpClient): HttpClient

/**
 * Will use its parameters of constructor to create several [DefaultKtorRequestsExecutor] and use them in [execute]
 * and [close] operations
 *
 * This [BaseRequestsExecutor] has been created for LinuxX64 target due to its inability of requests paralleling
 *
 * Under the hood on each [execute] it will take [DefaultKtorRequestsExecutor] and mark it as busy, execute
 * [Request], free up taken [DefaultKtorRequestsExecutor] and return (or throw) the result of execution
 *
 * @param requestExecutorsCount Amount of [DefaultKtorRequestsExecutor] which will be created and used under the
 * hood
 */
class MultipleClientKtorRequestsExecutor (
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    callsFactories: List<KtorCallFactory>,
    excludeDefaultFactories: Boolean,
    requestsLimiter: RequestLimiter,
    jsonFormatter: Json,
    pipelineStepsHolder: TelegramBotPipelinesHandler,
    requestExecutorsCount: Int,
    logger: KSLog,
    clientFactory: () -> HttpClient
) : BaseRequestsExecutor(telegramAPIUrlsKeeper) {
    private val requestExecutors = (0 until requestExecutorsCount).map {
        DefaultKtorRequestsExecutor(
            telegramAPIUrlsKeeper,
            clientFactory(),
            callsFactories,
            excludeDefaultFactories,
            requestsLimiter,
            jsonFormatter,
            pipelineStepsHolder,
            logger,
            Unit
        )
    }.toSet()
    private val freeClients = MutableStateFlow<Set<DefaultKtorRequestsExecutor>>(requestExecutors)
    private val clientAllocationMutex = Mutex()
    private val takerFlow = freeClients.mapNotNull {
        clientAllocationMutex.withLock {
            freeClients.value.firstOrNull() ?.also {
                freeClients.value -= it
            } ?: return@mapNotNull null
        }
    }

    internal constructor(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
        client: HttpClient,
        callsFactories: List<KtorCallFactory>,
        excludeDefaultFactories: Boolean,
        requestsLimiter: RequestLimiter,
        jsonFormatter: Json,
        pipelineStepsHolder: TelegramBotPipelinesHandler,
        logger: KSLog,
        diff: Unit
    ) : this(
        telegramAPIUrlsKeeper,
        callsFactories,
        excludeDefaultFactories,
        requestsLimiter,
        jsonFormatter,
        pipelineStepsHolder,
        client.engineConfig.threadsCount,
        logger,
        { platformClientCopy(client) }
    )

    private suspend fun prepareRequestsExecutor(): DefaultKtorRequestsExecutor {
        return takerFlow.first()
    }

    private suspend fun freeRequestsExecutor(client: DefaultKtorRequestsExecutor) {
        clientAllocationMutex.withLock {
            freeClients.value += client
        }
    }

    private suspend fun <T> withRequestExecutor(block: suspend (DefaultKtorRequestsExecutor) -> T): T {
        val requestsExecutor = prepareRequestsExecutor()
        val result = runCatchingSafely {
            block(requestsExecutor)
        }
        freeRequestsExecutor(requestsExecutor)
        return result.getOrThrow()
    }

    override suspend fun <T : Any> execute(request: Request<T>): T = withRequestExecutor {
        it.execute(request)
    }

    override fun close() {
        requestExecutors.forEach {
            it.close()
        }
    }
}
