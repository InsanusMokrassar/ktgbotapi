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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<ChatMemberUpdated> = expectFlow(
    errorFactory
) {
    (it as? O) ?.data.let(::listOfNotNull)
}

fun BehaviourContext.waitChatMemberUpdated(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<ChatMemberUpdatedUpdate>(errorFactory)

fun BehaviourContext.waitCommonChatMemberUpdated(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitChatMemberUpdatedWithFilter<CommonChatMemberUpdatedUpdate>(errorFactory)

fun BehaviourContext.waitMyChatMemberUpdated(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdatedWithFilter<MyChatMemberUpdatedUpdate>(errorFactory)

fun BehaviourContext.waitChatMemberJoined(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitChatMemberLeft(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitChatMemberSubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitChatMemberSubscriptionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitChatMemberUnsubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitChatMemberGotPromoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitChatMemberGotPromotionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitChatMemberGotDemoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitChatMemberBecameOwner(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitChatMemberCeasedOwnership(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitChatMemberGotRestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitChatMemberGotRestrictionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitChatMemberGotUnrestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitChatMemberKicked(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitChatMemberUpdated(errorFactory).filter { chatMemberKickedFilter(it) };

fun BehaviourContext.waitCommonChatMemberJoined(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitCommonChatMemberLeft(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitCommonChatMemberSubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitCommonChatMemberSubscriptionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberUnsubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotPromoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotPromotionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotDemoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitCommonChatMemberBecameOwner(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitCommonChatMemberCeasedOwnership(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotRestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotRestrictionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitCommonChatMemberGotUnrestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitCommonChatMemberKicked(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitCommonChatMemberUpdated(errorFactory).filter { chatMemberKickedFilter(it) };

fun BehaviourContext.waitMyChatMemberJoined(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberJoinedFilter(it) };

fun BehaviourContext.waitMyChatMemberLeft(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberLeftFilter(it) };

fun BehaviourContext.waitMyChatMemberSubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberSubscribedFilter(it) };

fun BehaviourContext.waitMyChatMemberSubscriptionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberSubscriptionChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberUnsubscribed(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberUnsubscribedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotPromoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotPromotedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotPromotionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotPromotionChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotDemoted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotDemotedFilter(it) };

fun BehaviourContext.waitMyChatMemberBecameOwner(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberBecameOwnerFilter(it) };

fun BehaviourContext.waitMyChatMemberCeasedOwnership(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberCeasedOwnershipFilter(it) };

fun BehaviourContext.waitMyChatMemberGotRestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotRestrictionChanged(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotRestrictionsChangedFilter(it) };

fun BehaviourContext.waitMyChatMemberGotUnrestricted(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberGotUnrestrictedFilter(it) };

fun BehaviourContext.waitMyChatMemberKicked(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitMyChatMemberUpdated(errorFactory).filter { chatMemberKickedFilter(it) };
