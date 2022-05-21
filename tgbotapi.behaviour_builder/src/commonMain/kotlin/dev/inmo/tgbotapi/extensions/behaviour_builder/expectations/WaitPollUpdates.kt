package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPollUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.*
import kotlinx.coroutines.flow.toList

typealias PollMapper<T> = suspend T.() -> T?

private suspend inline fun <reified O> BehaviourContext.waitPolls(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPollUpdate() ?.data as? O ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<Poll>? = null
) = waitPolls(count, initRequest, errorFactory, filter)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitQuizPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<QuizPoll>? = null
) = waitPolls(count, initRequest, errorFactory, filter)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitRegularPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<RegularPoll>? = null
) = waitPolls(count, initRequest, errorFactory, filter)
