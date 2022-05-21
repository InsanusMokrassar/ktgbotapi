package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asPollAnswerUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.polls.PollAnswer
import kotlinx.coroutines.flow.toList

typealias PollAnswerMapper = suspend PollAnswer.() -> PollAnswer?

private suspend inline fun <reified O : PollAnswer> BehaviourContext.waitPollAnswers(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<O>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asPollAnswerUpdate() ?.data as? O ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        listOf(data)
    } else {
        emptyList()
    }
}.toList().toList()

/**
 * This wait will be triggered only for stopped polls and polls, which are sent by the bot
 */
suspend fun BehaviourContext.waitPollAnswers(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PollAnswer>? = null
) = waitPollAnswers(count, initRequest, errorFactory, filter)
