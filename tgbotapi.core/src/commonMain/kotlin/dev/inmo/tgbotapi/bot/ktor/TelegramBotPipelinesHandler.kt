package dev.inmo.tgbotapi.bot.ktor

import dev.inmo.tgbotapi.requests.abstracts.Request

interface TelegramBotPipelinesHandler {
    /**
     * Will be called when any exception will happen due to the [request] handling. If returns value - that value
     * will be returned from [dev.inmo.tgbotapi.bot.RequestsExecutor.execute] instead
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
