package dev.inmo.tgbotapi.bot.ktor.middlewares

import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

class TelegramBotMiddleware(
    internal val onRequestException: (suspend (request: Request<*>, t: Throwable?) -> Any?)? = null,
    internal val onBeforeSearchCallFactory: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Unit)? = null,
    internal val onBeforeCallFactoryMakeCall: (suspend (request: Request<*>, potentialFactory: KtorCallFactory) -> Unit)? = null,
    internal val onAfterCallFactoryMakeCall: (suspend (result: Any?, request: Request<*>, potentialFactory: KtorCallFactory) -> Any?)? = null,
    internal val onRequestResultPresented: (suspend (result: Any?, request: Request<*>, resultCallFactory: KtorCallFactory, callsFactories: List<KtorCallFactory>) -> Any?)? = null,
    internal val onRequestResultAbsent: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Any?)? = null,
    internal val onRequestReturnResult: (suspend (result: Result<*>, request: Request<*>, callsFactories: List<KtorCallFactory>) -> Result<Any?>?)? = null,
) : TelegramBotPipelinesHandler {
    object ResultAbsence : Throwable()
    override suspend fun <T : Any> onRequestException(request: Request<T>, t: Throwable): T? {
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
        return onAfterCallFactoryMakeCall ?.invoke(result, request, potentialFactory) as? T
    }

    override suspend fun <T : Any> onRequestResultPresented(
        result: T,
        request: Request<T>,
        resultCallFactory: KtorCallFactory,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return onRequestResultPresented ?.invoke(result, request, resultCallFactory, callsFactories) as? T
    }

    override suspend fun <T : Any> onRequestResultAbsent(
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return onRequestResultAbsent ?.invoke(request, callsFactories) as? T
    }

    override suspend fun <T : Any> onRequestReturnResult(
        result: Result<T>,
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): Result<T> {
        return onRequestReturnResult ?.invoke(result, request, callsFactories) as? Result<T> ?: Result.failure(ResultAbsence)
    }

    companion object {
        fun build(block: TelegramBotMiddlewareBuilder.() -> Unit): TelegramBotMiddleware = TelegramBotMiddlewareBuilder().apply(block).build()
    }
}