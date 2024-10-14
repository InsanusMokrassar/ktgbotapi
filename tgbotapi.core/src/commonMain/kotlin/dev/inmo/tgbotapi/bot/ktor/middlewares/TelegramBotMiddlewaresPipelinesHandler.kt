package dev.inmo.tgbotapi.bot.ktor.middlewares

import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

class TelegramBotMiddlewaresPipelinesHandler(
    private val middlewares: List<TelegramBotMiddleware>
) : TelegramBotPipelinesHandler {
    override suspend fun <T : Any> onRequestException(request: Request<T>, t: Throwable): T? {
        return super.onRequestException(request, t)
    }

    override suspend fun onBeforeSearchCallFactory(request: Request<*>, callsFactories: List<KtorCallFactory>) {
        super.onBeforeSearchCallFactory(request, callsFactories)
    }

    override suspend fun onBeforeCallFactoryMakeCall(request: Request<*>, potentialFactory: KtorCallFactory) {
        super.onBeforeCallFactoryMakeCall(request, potentialFactory)
    }

    override suspend fun <T : Any> onAfterCallFactoryMakeCall(
        result: T?,
        request: Request<T>,
        potentialFactory: KtorCallFactory
    ): T? {
        return super.onAfterCallFactoryMakeCall(result, request, potentialFactory)
    }

    override suspend fun <T : Any> onRequestResultPresented(
        result: T,
        request: Request<T>,
        resultCallFactory: KtorCallFactory,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return super.onRequestResultPresented(result, request, resultCallFactory, callsFactories)
    }

    override suspend fun <T : Any> onRequestResultAbsent(
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): T? {
        return super.onRequestResultAbsent(request, callsFactories)
    }

    override suspend fun <T : Any> onRequestReturnResult(
        result: Result<T>,
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): Result<T> {
        return super.onRequestReturnResult(result, request, callsFactories)
    }
}
