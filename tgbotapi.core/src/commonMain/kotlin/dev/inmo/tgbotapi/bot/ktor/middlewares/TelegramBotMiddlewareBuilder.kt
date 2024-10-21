package dev.inmo.tgbotapi.bot.ktor.middlewares

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.bot.ktor.KtorCallFactory
import dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler
import dev.inmo.tgbotapi.requests.abstracts.Request

@Warning("This API is experimental and subject of changes")
class TelegramBotMiddlewareBuilder {
    var onRequestException: (suspend (request: Request<*>, t: Throwable?) -> Any?)? = null
    var onBeforeSearchCallFactory: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Unit)? = null
    var onBeforeCallFactoryMakeCall: (suspend (request: Request<*>, potentialFactory: KtorCallFactory) -> Unit)? = null
    var onAfterCallFactoryMakeCall: (suspend (result: Any?, request: Request<*>, potentialFactory: KtorCallFactory) -> Any?)? = null
    var onRequestResultPresented: (suspend (result: Any?, request: Request<*>, resultCallFactory: KtorCallFactory, callsFactories: List<KtorCallFactory>) -> Any?)? = null
    var onRequestResultAbsent: (suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Any?)? = null
    var onRequestReturnResult: (suspend (result: Result<*>, request: Request<*>, callsFactories: List<KtorCallFactory>) -> Result<Any?>?)? = null

    /**
     * Useful way to set [onRequestException]
     */
    fun doOnRequestException(block: suspend (request: Request<*>, t: Throwable?) -> Any?) {
        onRequestException = block
    }
    /**
     * Useful way to set [onBeforeSearchCallFactory]
     */
    fun doOnBeforeSearchCallFactory(block: suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Unit) {
        onBeforeSearchCallFactory = block
    }
    /**
     * Useful way to set [onBeforeCallFactoryMakeCall]
     */
    fun doOnBeforeCallFactoryMakeCall(block: suspend (request: Request<*>, potentialFactory: KtorCallFactory) -> Unit) {
        onBeforeCallFactoryMakeCall = block
    }
    /**
     * Useful way to set [onAfterCallFactoryMakeCall]
     */
    fun doOnAfterCallFactoryMakeCall(block: suspend (result: Any?, request: Request<*>, potentialFactory: KtorCallFactory) -> Any?) {
        onAfterCallFactoryMakeCall = block
    }
    /**
     * Useful way to set [onRequestResultPresented]
     */
    fun doOnRequestResultPresented(block: suspend (result: Any?, request: Request<*>, resultCallFactory: KtorCallFactory, callsFactories: List<KtorCallFactory>) -> Any?) {
        onRequestResultPresented = block
    }
    /**
     * Useful way to set [onRequestResultAbsent]
     */
    fun doOnRequestResultAbsent(block: suspend (request: Request<*>, callsFactories: List<KtorCallFactory>) -> Any?) {
        onRequestResultAbsent = block
    }
    /**
     * Useful way to set [onRequestReturnResult]
     */
    fun doOnRequestReturnResult(block: suspend (result: Result<*>, request: Request<*>, callsFactories: List<KtorCallFactory>) -> Result<Any?>?) {
        onRequestReturnResult = block
    }

    @Warning("This API is experimental and subject of changes")
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
        @Warning("This API is experimental and subject of changes")
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
