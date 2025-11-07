package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.tgbotapi.requests.abstracts.Request

interface TelegramBotPipelinesHandler {
    /**
     * Will be called when any exception will happen due to the [request] handling inside of limiter block. This method
     * will be called for each exception happened during call factory call
     */
    suspend fun <T: Any> onRequestExceptionInLimiter(
        request: Request<T>,
        t: Throwable
    ): T? = null

    /**
     * Will be called when any exception will happen due to the [request] handling. If returns value - that value
     * will be returned from [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead. In difference with
     * [onRequestExceptionInLimiter], this method will be called only AFTER
     * [dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter] will pass result of call factory execution outside of
     * its [dev.inmo.tgbotapi.bot.settings.limiters.RequestLimiter.limit] function
     *
     * @see dev.inmo.tgbotapi.bot.ktor.base.DefaultKtorRequestsExecutor
     * @see dev.inmo.tgbotapi.bot.settings.limiters.ExceptionsOnlyLimiter
     */
    suspend fun <T: Any> onRequestException(
        request: Request<T>,
        t: Throwable
    ): T? = null

    /**
     * Will always be called before requests executor will check all [callsFactories] for an opportunity to make call of
     * [request]
     */
    suspend fun onBeforeSearchCallFactory(
        request: Request<*>,
        callsFactories: List<KtorCallFactory>
    ) {}

    /**
     * Will always be called before [potentialFactory] will try to make [request]
     */
    suspend fun onBeforeCallFactoryMakeCall(
        request: Request<*>,
        potentialFactory: KtorCallFactory
    ) {}

    /**
     * Will always be called after [potentialFactory] has tried to make [request] and got some [result]. If returns
     * value - that value will be returned from [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead
     */
    suspend fun <T: Any> onAfterCallFactoryMakeCall(
        result: T?,
        request: Request<T>,
        potentialFactory: KtorCallFactory
    ): T? = result

    /**
     * Will be called when [resultCallFactory] is the [KtorCallFactory] from [callsFactories] which has successfully
     * handled [request] and returned [result]. If returns value - that value will be returned from
     * [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead
     */
    suspend fun <T: Any> onRequestResultPresented(
        result: T,
        request: Request<T>,
        resultCallFactory: KtorCallFactory,
        callsFactories: List<KtorCallFactory>
    ): T? = result

    /**
     * Will be called when there is no [KtorCallFactory] from [callsFactories] which may handle [request]. If returns
     * value - that value will be returned from [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead
     */
    suspend fun <T: Any> onRequestResultAbsent(
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): T? = null

    /**
     * This step will be called when the [result] has been retrieved (or exception has happened). If returns value -
     * that value will be returned from [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead
     */
    suspend fun <T: Any> onRequestReturnResult(
        result: Result<T>,
        request: Request<T>,
        callsFactories: List<KtorCallFactory>
    ): Result<T> = result

    companion object : TelegramBotPipelinesHandler
}

@Deprecated("Renamed", ReplaceWith("TelegramBotPipelinesHandler", "dev.inmo.tgbotapi.bot.ktor.TelegramBotPipelinesHandler"))
typealias KtorPipelineStepsHolder = TelegramBotPipelinesHandler
