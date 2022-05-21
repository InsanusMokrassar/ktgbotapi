package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPreCheckoutQueryUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import kotlinx.coroutines.flow.toList

typealias PreCheckoutQueryMapper = suspend PreCheckoutQuery.() -> PreCheckoutQuery?

private suspend inline fun <reified O : PreCheckoutQuery> BehaviourContext.waitPreCheckoutQueries(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPreCheckoutQueryUpdate() ?.data as? O ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()


suspend fun BehaviourContext.waitPreCheckoutQueries(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PreCheckoutQuery>? = null
) : List<PreCheckoutQuery> = waitPreCheckoutQueries(
    count,
    initRequest,
    errorFactory,
    filter
)
