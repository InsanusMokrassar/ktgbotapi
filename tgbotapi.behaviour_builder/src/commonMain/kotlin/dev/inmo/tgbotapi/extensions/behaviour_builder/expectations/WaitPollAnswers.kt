package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPollAnswerUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.PollAnswer
import kotlinx.coroutines.flow.toList

typealias PollAnswerMapper = suspend PollAnswer.() -> PollAnswer?

private suspend fun <O> BehaviourContext.waitPollsAnswers(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<PollAnswer>? = null,
    mapper: suspend PollAnswer.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPollAnswerUpdate() ?.data
    if (data != null && (filter == null || filter(data))) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()


private suspend inline fun BehaviourContext.waitPollAnswers(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: SimpleFilter<PollAnswer>? = null,
    noinline mapper: PollAnswerMapper? = null
) : List<PollAnswer> = this@waitPollAnswers.waitPollsAnswers<PollAnswer>(
    count,
    initRequest,
    errorFactory,
    filter ?.let {
        {
            (it as? PollAnswer) ?.let { filter(it) } == true
        }
    }
) {
    if (this is PollAnswer) {
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
suspend fun BehaviourContext.waitPollAnswers(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PollAnswer>? = null,
    mapper: PollAnswerMapper? = null
) = waitPollAnswers(count, initRequest, errorFactory, filter, mapper)
