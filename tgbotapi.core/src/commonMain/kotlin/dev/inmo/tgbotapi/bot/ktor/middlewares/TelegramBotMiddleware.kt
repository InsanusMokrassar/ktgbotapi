package dev.inmo.tgbotapi.bot.ktor.middlewares

import com.benasher44.uuid.uuid4
import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

/**
 * @param onRequestException Will be called when some exception happen during [Request] handling. Non-null result of
 * lambda will be used as the result of request handling
 * @param onBeforeSearchCallFactory Will be called when telegram bot starts to choose which [KtorCallFactory] will handle
 * [Request]
 * @param onBeforeCallFactoryMakeCall Will be called when telegram bot trying to use [KtorCallFactory] as potential
 * handler for [Request]
 * @param onAfterCallFactoryMakeCall Will be called when [KtorCallFactory] made call. Non-null result of
 * lambda will be used as the result of request handling
 * @param onRequestResultPresented Will be called when [KtorCallFactory] **or** [TelegramBotPipelinesHandler]/[TelegramBotMiddleware]
 * returned non-null result. Non-null result of lambda will be used as the result of request handling
 * @param onRequestResultAbsent Will be called when some there is no any result of [Request] handling. Non-null result of
 * lambda will be used as the result of request handling
 * @param onRequestReturnResult Latest lambda before result returning. Will be called after all previous stages.
 * Non-null result of lambda will be used as the result of request handling
 */
@Warning("This API is experimental and subject of changes")
open class TelegramBotMiddleware(
    internal val onRequestException: (suspend (request: Request<*>, t: Throwable?) -> Any?)? = null,
    internal val onBeforeSearchCallFactory: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Unit)? = null,
    internal val onBeforeCallFactoryMakeCall: (suspend (request: Request<*>, potentialFactory: KtorCallFactory) -> Unit)? = null,
    internal val onAfterCallFactoryMakeCall: (suspend (result: Any?, request: Request<*>, potentialFactory: KtorCallFactory) -> Any?)? = null,
    internal val onRequestResultPresented: (suspend (result: Any, request: Request<*>, resultCallFactory: KtorCallFactory, callsFactories: List<KtorCallFactory>) -> Any?)? = null,
    internal val onRequestResultAbsent: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Any?)? = null,
    internal val onRequestReturnResult: (suspend (result: Result<*>, request: Request<*>, callsFactories: List<KtorCallFactory>) -> Result<Any?>?)? = null,
    val id: String = uuid4().toString()
) : TelegramBotPipelinesHandler {
    object ResultAbsence : Throwable()
    override suspend fun <T : Any> onRequestException(request: Request<T>, t: Throwable): T? {
        @Suppress("UNCHECKED_CAST")
        return onRequestException ?.invoke(request, t) as? T
    }

    override suspend fun onBeforeSearchCallFactory(request: Request<*>, callsFactories: List<KtorCallFactory>) {
        onBeforeSearchCallFactory ?.invoke(request, callsFactories)
    }

    override suspend fun onBeforeCallFactoryMakeCall(request: Request<*>, potentialFactory: KtorCallFactory) {
        onBeforeCallFactoryMakeCall ?.invoke(request, potentialFactory)
    }

    override suspend fun <T : Any> onAfterCallFactoryMakeCall(
        result: T?,
        request: Request<T>,
        potentialFactory: KtorCallFactory
    ): T? {
        @Suppress("UNCHECKED_CAST")
        return onAfterCallFactoryMakeCall ?.invoke(result, request, potentialFactory) as? T
    }

    override suspend fun <T : Any> onRequestResultPresented(
        result: T,
        request: Request<T>,
        resultCallFactory: KtorCallFactory,
        callsFactories: List<KtorCallFactory>
    ): T? {
        @Suppress("UNCHECKED_CAST")
        return onRequestResultPresented ?.invoke(result, request, resultCallFactory, callsFactories) as? T
    }

    override suspend fun <T : Any> onRequestResultAbsent(
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): T? {
        @Suppress("UNCHECKED_CAST")
        return onRequestResultAbsent ?.invoke(request, callsFactories) as? T
    }

    override suspend fun <T : Any> onRequestReturnResult(
        result: Result<T>,
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): Result<T> {
        @Suppress("UNCHECKED_CAST")
        return onRequestReturnResult ?.invoke(result, request, callsFactories) as? Result<T> ?: Result.failure(ResultAbsence)
    }

    companion object {
        @Warning("This API is experimental and subject of changes")
        fun build(block: TelegramBotMiddlewareBuilder.() -> Unit): TelegramBotMiddleware = TelegramBotMiddlewareBuilder().apply(block).build()
    }
}