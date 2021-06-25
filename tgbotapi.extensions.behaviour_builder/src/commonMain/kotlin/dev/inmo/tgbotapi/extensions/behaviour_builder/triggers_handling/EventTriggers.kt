package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling


import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.optionallyWrapWithLaunch
import dev.inmo.tgbotapi.extensions.utils.asBaseSentMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asChatEventMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

internal suspend inline fun <reified T : ChatEvent> BehaviourContext.onEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (ChatEventMessage<T>) -> Boolean)? = null,
    performInParallel: Boolean = true,
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
}.subscribeSafelyWithoutExceptions(
    scope,
    optionallyWrapWithLaunch(performInParallel) { triggerMessage ->
        doInSubContextWithUpdatesFilter(
            updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
                { it.sourceChat() ?.id ?.chatId == triggerMessage.chat.id.chatId }
            } else null,
            stopOnCompletion = false
        ) {
            scenarioReceiver(triggerMessage)
        }
    }
)

suspend fun BehaviourContext.onChannelEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChatEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatStartedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatStarted>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatStarted>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEndedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatEnded>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEnded>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatParticipantsInvitedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatParticipantsInvited>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatParticipantsInvited>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMessageAutoDeleteTimerChangedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<MessageAutoDeleteTimerChanged>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onCommonEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<CommonEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onGroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupEvent>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)

suspend fun BehaviourContext.onChannelChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelChatCreated>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onDeleteChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<DeleteChatPhoto>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onGroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupChatCreated>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onLeftChatMember(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<LeftChatMember>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onNewChatMembers(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatMembers>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onNewChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatPhoto>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onNewChatTitle(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatTitle>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onPinnedMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<PinnedMessage>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onProximityAlertTriggered(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ProximityAlertTriggered>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupChatCreated>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
