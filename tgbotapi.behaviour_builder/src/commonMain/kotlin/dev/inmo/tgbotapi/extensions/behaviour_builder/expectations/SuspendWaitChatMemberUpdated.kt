package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onChatMemberUpdatedInternal
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatChatMemberUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdatedWithFilter(
    initRequest: Request<*>,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatMemberUpdated> = expectFlow(
    initRequest,
    errorFactory
) {
    (it as? O) ?.data.let(::listOfNotNull)
}

suspend fun BehaviourContext.waitChatMemberUpdated(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(initRequest, errorFactory)

suspend fun BehaviourContext.waitCommonChatMemberUpdated(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(initRequest, errorFactory)

suspend fun BehaviourContext.waitMyChatMemberUpdated(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(initRequest, errorFactory)

suspend fun BehaviourContext.waitChatMemberJoined(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

suspend fun BehaviourContext.waitChatMemberLeft(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

suspend fun BehaviourContext.waitChatMemberSubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

suspend fun BehaviourContext.waitChatMemberSubscriptionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

suspend fun BehaviourContext.waitChatMemberUnsubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotPromoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotPromotionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotDemoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

suspend fun BehaviourContext.waitChatMemberBecameOwner(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

suspend fun BehaviourContext.waitChatMemberCeasedOwnership(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotRestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotRestrictionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

suspend fun BehaviourContext.waitChatMemberGotUnrestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

suspend fun BehaviourContext.waitChatMemberKicked(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberJoined(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberLeft(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberSubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberSubscriptionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberUnsubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotPromoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotPromotionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotDemoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberBecameOwner(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberCeasedOwnership(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotRestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotRestrictionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberGotUnrestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

suspend fun BehaviourContext.waitCommonChatMemberKicked(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberJoined(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberLeft(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberSubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberSubscriptionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberUnsubscribed(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotPromoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotPromotionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotDemoted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberBecameOwner(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberCeasedOwnership(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotRestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotRestrictionChanged(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberGotUnrestricted(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

suspend fun BehaviourContext.waitMyChatMemberKicked(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };
