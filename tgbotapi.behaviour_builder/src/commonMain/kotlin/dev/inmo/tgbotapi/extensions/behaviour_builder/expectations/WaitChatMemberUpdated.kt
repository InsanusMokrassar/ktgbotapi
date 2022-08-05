package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias ChatMemberUpdatedMapper<T> = suspend T.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdatedWithFilter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatMemberUpdated> = expectFlow(
    initRequest,
    errorFactory
) {
    (it as? O) ?.data.let(::listOfNotNull)
}

suspend fun BehaviourContext.waitChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(initRequest, errorFactory)

suspend fun BehaviourContext.waitCommonChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(initRequest, errorFactory)

suspend fun BehaviourContext.waitMyChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(initRequest, errorFactory)
