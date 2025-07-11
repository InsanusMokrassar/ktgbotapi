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

typealias ChatMemberUpdatedMapper<T> = suspend T.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O : ChatMemberUpdatedUpdate> BehaviourContext.waitChatMemberUpdatedWithFilter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatMemberUpdated> = expectFlow(
    initRequest,
    errorFactory
) {
    (it as? O) ?.data.let(::listOfNotNull)
}

fun BehaviourContext.waitChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(initRequest, errorFactory)

fun BehaviourContext.waitCommonChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(initRequest, errorFactory)

fun BehaviourContext.waitMyChatMemberUpdated(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(initRequest, errorFactory)

fun BehaviourContext.waitChatMemberJoined(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitChatMemberLeft(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitChatMemberSubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitChatMemberSubscriptionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitChatMemberUnsubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitChatMemberGotPromoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitChatMemberGotPromotionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitChatMemberGotDemoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitChatMemberBecameOwner(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitChatMemberCeasedOwnership(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitChatMemberGotRestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitChatMemberGotRestrictionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitChatMemberGotUnrestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitChatMemberKicked(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };

fun BehaviourContext.waitCommonChatMemberJoined(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitCommonChatMemberLeft(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitCommonChatMemberSubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitCommonChatMemberSubscriptionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberUnsubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotPromoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotPromotionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotDemoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitCommonChatMemberBecameOwner(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitCommonChatMemberCeasedOwnership(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotRestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotRestrictionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotUnrestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitCommonChatMemberKicked(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };

fun BehaviourContext.waitMyChatMemberJoined(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitMyChatMemberLeft(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitMyChatMemberSubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitMyChatMemberSubscriptionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberUnsubscribed(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotPromoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotPromotionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotDemoted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitMyChatMemberBecameOwner(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitMyChatMemberCeasedOwnership(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitMyChatMemberGotRestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotRestrictionChanged(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotUnrestricted(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitMyChatMemberKicked(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(initRequest, errorFactory).filter { chatMemberKickedFilter(it) };
