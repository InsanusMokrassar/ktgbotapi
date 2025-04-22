package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.chatMessageReactionUpdatedUpdateOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : ChatMessageReactionUpdated> BehaviourContext.waitChatMessageReactionUpdated(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
): Flow<O> =
    expectFlow(
        initRequest,
        errorFactory,
    ) {
        (it.chatMessageReactionUpdatedUpdateOrNull() ?.data as? O).let(::listOfNotNull)
    }

suspend fun BehaviourContext.waitChatMessageReactionUpdatedByUser(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMessageReactionUpdated<ChatMessageReactionUpdated.ByUser>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatMessageReactionUpdatedByChat(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMessageReactionUpdated<ChatMessageReactionUpdated.ByChat>(initRequest, errorFactory)
