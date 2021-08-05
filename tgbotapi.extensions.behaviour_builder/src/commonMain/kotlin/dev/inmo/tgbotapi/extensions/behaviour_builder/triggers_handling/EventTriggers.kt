package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling


import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asBaseSentMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

internal suspend inline fun <reified T : ChatEvent> BehaviourContext.onEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: SimpleFilter<ChatEventMessage<T>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<T>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<T>>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asBaseSentMessageUpdate() ?.data ?.asChatEventMessage() ?.let { message ->
        if (message.chatEvent is T) {
            val adaptedMessage = message as ChatEventMessage<T>
            if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptionsAsync(
    scope,
    markerFactory::invoke
) { triggerMessage ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerMessage.chat.id.chatId }
        } else null,
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerMessage)
    }
}

suspend fun BehaviourContext.onChannelEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<ChannelEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<ChatEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatStartedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatStarted>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatStarted>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatStarted>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEndedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatEnded>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatEnded>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEnded>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatParticipantsInvitedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<VoiceChatParticipantsInvited>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<VoiceChatParticipantsInvited>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatParticipantsInvited>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onMessageAutoDeleteTimerChangedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<MessageAutoDeleteTimerChanged>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<MessageAutoDeleteTimerChanged>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onCommonEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<CommonEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<CommonEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onGroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<GroupEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<SupergroupEvent>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupEvent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)

suspend fun BehaviourContext.onChannelChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<ChannelChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ChannelChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onDeleteChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<DeleteChatPhoto>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<DeleteChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onGroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<GroupChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<GroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onLeftChatMember(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<LeftChatMember>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<LeftChatMember>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onNewChatMembers(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatMembers>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatMembers>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onNewChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatPhoto>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatPhoto>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onNewChatTitle(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<NewChatTitle>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<NewChatTitle>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onPinnedMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<PinnedMessage>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<PinnedMessage>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onProximityAlertTriggered(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<ProximityAlertTriggered>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<ProximityAlertTriggered>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<ChatEventMessage<SupergroupChatCreated>>? = null,
    markerFactory: MarkerFactory<in ChatEventMessage<SupergroupChatCreated>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, markerFactory, scenarioReceiver)
