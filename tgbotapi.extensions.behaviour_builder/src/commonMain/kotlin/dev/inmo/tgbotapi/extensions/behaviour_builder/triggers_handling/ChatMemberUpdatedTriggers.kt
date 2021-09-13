@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.ChatMemberUpdatedFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatChatMemberUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified U : ChatMemberUpdatedUpdate> BehaviourContext.onChatMemberUpdatedInternal(
    noinline initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    ((it as? U) ?.data) ?.let(::listOfNotNull)
}

suspend fun BehaviourContext.onChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<ChatMemberUpdatedUpdate>(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) ChatMemberUpdatedFilterByChat else null,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onCommonChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<CommonChatMemberUpdatedUpdate>(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) ChatMemberUpdatedFilterByChat else null,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onMyChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<MyChatMemberUpdatedUpdate>(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) ChatMemberUpdatedFilterByChat else null,
    markerFactory,
    scenarioReceiver
)


suspend fun BehaviourContext.onChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<ChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onCommonChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<CommonChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onMyChatMemberUpdated(
    initialFilter: SimpleFilter<ChatMemberUpdated>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update>? = ChatMemberUpdatedFilterByChat,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<MyChatMemberUpdatedUpdate>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
