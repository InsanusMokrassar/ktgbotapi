package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.regularTextSourceOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import kotlinx.coroutines.flow.*

fun BehaviourContext.waitDeepLinks(
    initRequest: Request<*>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<ChatContentMessage<TextContent>, String>> = waitCommandMessage(
    command = "start",
    initRequest = initRequest,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    errorFactory = errorFactory
)
    .requireSingleCommand()
    .requireCommandAtStart()
    .flattenCommandsWithParams().mapNotNull {
        it.first to (it.second.second.singleOrNull() ?.regularTextSourceOrNull() ?.source ?.removePrefix(" ") ?: return@mapNotNull null)
    }

fun BehaviourContext.waitDeepLinks(
    regex: Regex,
    initRequest: Request<*>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<ChatContentMessage<TextContent>, String>> = waitDeepLinks(
    initRequest = initRequest,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    errorFactory = errorFactory
).filter {
    regex.matches(it.second)
}

fun BehaviourContext.waitDeepLinks(
    deepLink: String,
    initRequest: Request<*>? = null,
    excludeCommandsToOtherBots: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<ChatContentMessage<TextContent>, String>> = waitDeepLinks(
    regex = Regex(pattern = "^$deepLink$"),
    initRequest = initRequest,
    excludeCommandsToOtherBots = excludeCommandsToOtherBots,
    errorFactory = errorFactory
)
