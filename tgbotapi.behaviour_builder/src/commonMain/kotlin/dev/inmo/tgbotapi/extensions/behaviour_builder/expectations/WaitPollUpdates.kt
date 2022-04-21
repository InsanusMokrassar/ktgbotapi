package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.*
import kotlinx.coroutines.flow.toList

typealias PollMapper<T> = suspend T.() -> T?

private suspend fun <O> BehaviourContext.waitPollsUpdates(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<Poll>? = null,
    mapper: suspend Poll.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPollUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun <reified T : Poll> BehaviourContext.waitPolls(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<T>? = null,
    noinline mapper: PollMapper<T>? = null
) : List<T> = this@waitPolls.waitPollsUpdates<T>(
    count,
    initRequest,
    errorFactory,
    filter ?.let {
        {
            (it as? T) ?.let { filter(it) } == true
        }
    }
) {
    if (this is T) {
        if (mapper == null) {
            this
        } else {
            mapper(this)
        }
    } else {
        null
    }
}

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<Poll>? = null,
    mapper: PollMapper<Poll>? = null
) = waitPolls(count, initRequest, errorFactory, filter, mapper)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitQuizPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<QuizPoll>? = null,
    mapper: PollMapper<QuizPoll>? = null
) = waitPolls(count, initRequest, errorFactory, filter, mapper)

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitRegularPollUpdates(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<RegularPoll>? = null,
    mapper: PollMapper<RegularPoll>? = null
) = waitPolls(count, initRequest, errorFactory, filter, mapper)
