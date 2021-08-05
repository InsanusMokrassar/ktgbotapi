package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelySkippingExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatChatMemberUpdatedMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

internal suspend inline fun <reified U : ChatMemberUpdatedUpdate> BehaviourContext.onChatMemberUpdatedInternal(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = flowsUpdatesFilter.expectFlow(bot) {
    (it as? U) ?.data ?.let { chatMemberUpdated ->
        if (additionalFilter == null || additionalFilter(chatMemberUpdated)) chatMemberUpdated else null
    }.let(::listOfNotNull)
}.subscribeSafelySkippingExceptionsAsync(
    scope,
    markerFactory::invoke
) { triggerChatMemberUpdated ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerChatMemberUpdated.chat.id.chatId }
        } else {
            null
        },
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerChatMemberUpdated)
    }
}

suspend fun BehaviourContext.onChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<ChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onCommonChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<CommonChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    markerFactory,
    scenarioReceiver
)

suspend fun BehaviourContext.onMyChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatMemberUpdated>? = null,
    markerFactory: MarkerFactory<ChatMemberUpdated, Any> = ByChatChatMemberUpdatedMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdatedInternal<MyChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    markerFactory,
    scenarioReceiver
)
