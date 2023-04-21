package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.regularTextSourceOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.RegularTextSource
import kotlinx.coroutines.flow.*

suspend fun BehaviourContext.waitDeepLinks(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<CommonMessage<TextContent>, String>> = waitCommandMessage(
    "start",
    initRequest,
    errorFactory
)
    .requireSingleCommand()
    .requireCommandAtStart()
    .flattenCommandsWithParams().mapNotNull {
        it.first to (it.second.second.singleOrNull() ?.regularTextSourceOrNull() ?.source ?.removePrefix(" ") ?: return@mapNotNull null)
    }

suspend fun BehaviourContext.waitDeepLinks(
    regex: Regex,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<CommonMessage<TextContent>, String>> = waitDeepLinks(initRequest, errorFactory).filter {
    regex.matches(it.second)
}

suspend fun BehaviourContext.waitDeepLinks(
    deepLink: String,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
): Flow<Pair<CommonMessage<TextContent>, String>> = waitDeepLinks(Regex("^$deepLink$"), initRequest, errorFactory)
