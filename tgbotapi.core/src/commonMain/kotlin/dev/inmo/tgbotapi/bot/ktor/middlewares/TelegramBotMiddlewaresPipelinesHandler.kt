package dev.inmo.tgbotapi.bot.ktor.middlewares

import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

class TelegramBotMiddlewaresPipelinesHandler(
    private val middlewares: List<TelegramBotMiddleware>
) : TelegramBotPipelinesHandler {
    override suspend fun <T : Any> onRequestException(request: Request<T>, t: Throwable): T? {
        return middlewares.firstNotNullOfOrNull {
            it.onRequestException(request, t)
        } ?: super.onRequestException(request, t)
    }

    override suspend fun onBeforeSearchCallFactory(request: Request<*>, callsFactories: List<KtorCallFactory>) {
        middlewares.forEach {
            it.onBeforeSearchCallFactory(request, callsFactories)
        }
    }

    override suspend fun onBeforeCallFactoryMakeCall(request: Request<*>, potentialFactory: KtorCallFactory) {
        middlewares.forEach {
            it.onBeforeCallFactoryMakeCall(request, potentialFactory)
        }
    }

    override suspend fun <T : Any> onAfterCallFactoryMakeCall(
        result: T?,
        request: Request<T>,
        potentialFactory: KtorCallFactory
    ): T? {
        return middlewares.firstNotNullOfOrNull {
            it.onAfterCallFactoryMakeCall(result, request, potentialFactory)
        } ?: super.onAfterCallFactoryMakeCall(result, request, potentialFactory)
    }

    override suspend fun <T : Any> onRequestResultPresented(
        result: T,
        request: Request<T>,
        resultCallFactory: KtorCallFactory,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return middlewares.firstNotNullOfOrNull {
            it.onRequestResultPresented(result, request, resultCallFactory, callsFactories)
        } ?: super.onRequestResultPresented(result, request, resultCallFactory, callsFactories)
    }

    override suspend fun <T : Any> onRequestResultAbsent(
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return middlewares.firstNotNullOfOrNull {
            it.onRequestResultAbsent(request, callsFactories)
        } ?: super.onRequestResultAbsent(request, callsFactories)
    }

    override suspend fun <T : Any> onRequestReturnResult(
        result: Result<T>,
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): Result<T> {
        return middlewares.firstNotNullOfOrNull {
            it.onRequestReturnResult(result, request, callsFactories).takeIf {
                it.onFailure { return@takeIf it !is TelegramBotMiddleware.ResultAbsence }
                true
            }
        } ?: super.onRequestReturnResult(result, request, callsFactories)
    }

    class Builder {
        val middlewares = mutableListOf<TelegramBotMiddleware>()

        fun addMiddleware(block: TelegramBotMiddlewareBuilder.() -> Unit) = middlewares.add(
            TelegramBotMiddleware.build(block)
        )

        fun build(): TelegramBotMiddlewaresPipelinesHandler = TelegramBotMiddlewaresPipelinesHandler(
            middlewares.toList()
        )
    }

    companion object {
        fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}
