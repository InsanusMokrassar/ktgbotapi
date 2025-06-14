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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    errorFactory
) {
    (it.callbackQueryUpdateOrNull() ?.data as? O).let(::listOfNotNull)
}


fun BehaviourContext.waitDataCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<DataCallbackQuery>(errorFactory)
fun BehaviourContext.waitGameShortNameCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<GameShortNameCallbackQuery>(errorFactory)
fun BehaviourContext.waitInlineMessageIdCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdCallbackQuery>(errorFactory)
fun BehaviourContext.waitInlineMessageIdDataCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdDataCallbackQuery>(errorFactory)
fun BehaviourContext.waitInlineMessageIdGameShortNameCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<InlineMessageIdGameShortNameCallbackQuery>(errorFactory)
fun BehaviourContext.waitMessageCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageCallbackQuery>(errorFactory)
fun BehaviourContext.waitMessageDataCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageDataCallbackQuery>(errorFactory)
fun BehaviourContext.waitMessageGameShortNameCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<MessageGameShortNameCallbackQuery>(errorFactory)
fun BehaviourContext.waitUnknownCallbackQuery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCallbackQueries<UnknownCallbackQueryType>(errorFactory)
