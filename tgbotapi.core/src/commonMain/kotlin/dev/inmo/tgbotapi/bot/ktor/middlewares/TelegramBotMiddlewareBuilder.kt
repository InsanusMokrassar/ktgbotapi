package dev.inmo.tgbotapi.bot.ktor.middlewares

import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

class TelegramBotMiddlewareBuilder {
    var onRequestException: (suspend (request: Request<*>, t: Throwable?) -> Any?)? = null
    var onBeforeSearchCallFactory: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Unit)? = null
    var onBeforeCallFactoryMakeCall: (suspend (request: Request<*>, potentialFactory: KtorCallFactory) -> Unit)? = null
    var onAfterCallFactoryMakeCall: (suspend (result: Any?, request: Request<*>, potentialFactory: KtorCallFactory) -> Any?)? = null
    var onRequestResultPresented: (suspend (result: Any?, request: Request<*>, resultCallFactory: KtorCallFactory, callsFactories: List<KtorCallFactory>) -> Any?)? = null
    var onRequestResultAbsent: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Any?)? = null
    var onRequestReturnResult: (suspend (result: Result<*>, request: Request<*>, callsFactories: List<KtorCallFactory>) -> Result<Any?>?)? = null


    fun build(): TelegramBotMiddleware {
        return TelegramBotMiddleware(
            onRequestException = onRequestException,
            onBeforeSearchCallFactory = onBeforeSearchCallFactory,
            onBeforeCallFactoryMakeCall = onBeforeCallFactoryMakeCall,
            onAfterCallFactoryMakeCall = onAfterCallFactoryMakeCall,
            onRequestResultPresented = onRequestResultPresented,
            onRequestResultAbsent = onRequestResultAbsent,
            onRequestReturnResult = onRequestReturnResult
        )
    }

    companion object {
        fun from(middleware: TelegramBotMiddleware, additionalSetup: TelegramBotMiddlewareBuilder.() -> Unit): TelegramBotMiddleware {
            return TelegramBotMiddlewareBuilder().apply {
                onRequestException = middleware.onRequestException
                onBeforeSearchCallFactory = middleware.onBeforeSearchCallFactory
                onBeforeCallFactoryMakeCall = middleware.onBeforeCallFactoryMakeCall
                onAfterCallFactoryMakeCall = middleware.onAfterCallFactoryMakeCall
                onRequestResultPresented = middleware.onRequestResultPresented
                onRequestResultAbsent = middleware.onRequestResultAbsent
                onRequestReturnResult = middleware.onRequestReturnResult
                additionalSetup()
            }.build()
        }
    }
}
