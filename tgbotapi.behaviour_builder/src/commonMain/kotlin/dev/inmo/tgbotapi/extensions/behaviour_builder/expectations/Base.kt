package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithResult
import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.flatten
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*

private val cancelledByFilterException = CancellationException("Cancelled by filter precreatedException")

typealias RequestBuilder<T> = suspend (Update) -> Request<T>
typealias NullableRequestBuilder<T> = suspend (Update) -> Request<T>?

/**
 * @param initRequest If not null, this request will be sent by [bot] before returning value
 * @param errorFactory If set, this factory will be used to produce requests in case when user have sent incorrect data
 * @param cancelRequestFactory If set, this factory will be used to produce requests in case when it is required to say
 * user that chain of scenario has been cancelled
 * @param cancelTrigger When this trigger returns true, chain is cancelled
 * @param filter It is main param, which will be called on each update. When it return not null, result will be returned
 * as is, but when it returns null, then will be called [cancelTrigger] (if it will return true - [cancelRequestFactory]
 * will be called too), [errorFactory] and then will be returned null
 */
@RiskFeature(lowLevelRiskFeatureMessage)
fun <T> FlowsUpdatesFilter.expectFlow(
    bot: TelegramBot,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> List<T>
): Flow<T> {
    val flow = allUpdatesFlow.map {
        val result = runCatching {
            filter(it)
        }
        if (result.isFailure || result.getOrThrow().isEmpty()) {
            if (cancelTrigger(it)) {
                cancelRequestFactory(it) ?.also {
                    safelyWithResult { bot.execute(it) }
                    throw cancelledByFilterException
                }
            }
            errorFactory(it) ?.also { errorRequest ->
                runCatching {
                    bot.execute(errorRequest)
                }
            }
            emptyList()
        } else {
            result.getOrThrow()
        }
    }.flatten()
    return flow {
        initRequest ?.also {
            runCatching {
                bot.execute(initRequest)
            }
        }
        emitAll(flow)
    }
}

/**
 * @param initRequest If not null, this request will be sent by [bot] before returning value
 * @param errorFactory If set, this factory will be used to produce requests in case when user have sent incorrect data
 * @param cancelRequestFactory If set, this factory will be used to produce requests in case when it is required to say
 * user that chain of scenario has been cancelled
 * @param cancelTrigger When this trigger returns true, chain is cancelled
 * @param filter It is main param, which will be called on each update. When it return not null, result will be returned
 * as is, but when it returns null, then will be called [cancelTrigger] (if it will return true - [cancelRequestFactory]
 * will be called too), [errorFactory] and then will be returned null
 */
@RiskFeature(lowLevelRiskFeatureMessage)
fun <T> BehaviourContext.expectFlow(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> List<T>
) = flowsUpdatesFilter.expectFlow(bot, initRequest, errorFactory, cancelRequestFactory, cancelTrigger, filter)

/**
 * @param initRequest If not null, this request will be sent by [bot] before returning value
 * @param errorFactory If set, this factory will be used to produce requests in case when user have sent incorrect data
 * @param cancelRequestFactory If set, this factory will be used to produce requests in case when it is required to say
 * user that chain of scenario has been cancelled
 * @param cancelTrigger When this trigger returns true, chain is cancelled
 * @param filter It is main param, which will be called on each update. When it return not null, result will be returned
 * as is, but when it returns null, then will be called [cancelTrigger] (if it will return true - [cancelRequestFactory]
 * will be called too), [errorFactory] and then will be returned null
 */
@RiskFeature(lowLevelRiskFeatureMessage)
suspend fun <T> FlowsUpdatesFilter.expectOne(
    bot: TelegramBot,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?
): T = expectFlow(bot, initRequest, errorFactory, cancelRequestFactory, cancelTrigger) {
    listOfNotNull(filter.invoke(it))
}.first()

/**
 * @param initRequest If not null, this request will be sent by [bot] before returning value
 * @param errorFactory If set, this factory will be used to produce requests in case when user have sent incorrect data
 * @param cancelRequestFactory If set, this factory will be used to produce requests in case when it is required to say
 * user that chain of scenario has been cancelled
 * @param cancelTrigger When this trigger returns true, chain is cancelled
 * @param filter It is main param, which will be called on each update. When it return not null, result will be returned
 * as is, but when it returns null, then will be called [cancelTrigger] (if it will return true - [cancelRequestFactory]
 * will be called too), [errorFactory] and then will be returned null
 */
@RiskFeature(lowLevelRiskFeatureMessage)
suspend fun <T> BehaviourContext.expectOne(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?
) = flowsUpdatesFilter.expectOne(bot, initRequest, errorFactory, cancelRequestFactory, cancelTrigger, filter)
