@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.callbackQueryUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.queries.callback.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias CallbackQueryMapper<T> = suspend T.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O> BehaviourContext.waitCallbackQueries(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    (it.callbackQueryUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}


fun BehaviourContext.waitDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<DataCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<GameShortNameCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdDataCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdGameShortNameCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitMessageCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitMessageDataCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageDataCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageGameShortNameCallbackQuery>(initRequest, errorFactory)
fun BehaviourContext.waitUnknownCallbackQuery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<UnknownCallbackQueryType>(initRequest, errorFactory)
