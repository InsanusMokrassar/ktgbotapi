@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.steps.expectations

import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.content.ContactContent
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import kotlinx.coroutines.flow.toList

typealias CallbackQueryMapper<T> = T.() -> T?

private suspend fun <O> Scenario.waitCallbackQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend CallbackQuery.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asCallbackQueryUpdate() ?.data ?.mapper()
}.toList().toList()


private suspend inline fun <reified T : CallbackQuery> Scenario.waitEvents(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: CallbackQueryMapper<T>? = null
) : List<T> = waitCallbackQueries<T>(
    count,
    initRequest,
    errorFactory
) {
    if (this is T) {
        if (filter == null) {
            this
        } else {
            filter(this)
        }
    } else {
        null
    }
}


suspend fun Scenario.waitDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<DataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<GameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitInlineMessageIdCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitInlineMessageIdDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdDataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitInlineMessageIdGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<InlineMessageIdGameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitMessageCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitMessageDataCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageDataCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitMessageGameShortNameCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<MessageGameShortNameCallbackQuery>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
suspend fun Scenario.waitUnknownCallbackQuery(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: CallbackQueryMapper<UnknownCallbackQueryType>? = null
) = waitEvents(count, initRequest, errorFactory, filter)
