package dev.inmo.tgbotapi.extensions.steps.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.steps.ScenarioReceiver
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

private val cancelledByFilterException = CancellationException("Cancelled by filter precreatedException")

typealias RequestBuilder<T> = suspend (Update) -> Request<T>
typealias NullableRequestBuilder<T> = suspend (Update) -> Request<T>?

@RiskFeature("This method is not very comfortable to use and too low-level. It is recommended to use methods which already included into library")
suspend fun <T> FlowsUpdatesFilter.expectFlow(
    bot: TelegramBot,
    initRequest: Request<*>? = null,
    count: Int? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?
): Flow<T> {
    val flow = allUpdatesFlow.mapNotNull {
        val result = safelyWithoutExceptions { filter(it) }
        if (result == null) {
            if (cancelTrigger(it)) {
                cancelRequestFactory(it) ?.also {
                    safelyWithoutExceptions { bot.execute(it) }
                    throw cancelledByFilterException
                }
            }
            errorFactory(it) ?.also { errorRequest ->
                safelyWithoutExceptions { bot.execute(errorRequest) }
            }
            null
        } else {
            result
        }
    }
    val result = if (count == null) {
        flow
    } else {
        flow.take(count)
    }
    initRequest ?.also { safelyWithoutExceptions { bot.execute(initRequest) } }
    return result
}

suspend fun <T> Scenario.expectFlow(
    initRequest: Request<*>? = null,
    count: Int? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?
) = flowsUpdatesFilter.expectFlow(bot, initRequest, count, errorFactory, cancelRequestFactory, cancelTrigger, filter)

@RiskFeature("This method is not very comfortable to use and too low-level. It is recommended to use methods which already included into library")
suspend fun <T> FlowsUpdatesFilter.expectOne(
    bot: TelegramBot,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?,
): T = expectFlow(bot, initRequest, 1, errorFactory, cancelRequestFactory, cancelTrigger, filter).first()

suspend fun <T> Scenario.expectOne(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    cancelRequestFactory: NullableRequestBuilder<*> = { null },
    cancelTrigger: suspend (Update) -> Boolean = { cancelRequestFactory(it) != null },
    filter: suspend (Update) -> T?
) = flowsUpdatesFilter.expectOne(bot, initRequest, errorFactory, cancelRequestFactory, cancelTrigger, filter)
