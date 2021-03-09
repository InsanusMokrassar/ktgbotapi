package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling


import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage

internal suspend inline fun <reified T : ChatEvent> BehaviourContext.onEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (ChatEventMessage<T>) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<T>>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asMessageUpdate() ?.data ?.asChatEventMessage() ?.let { message ->
        if (message.chatEvent is T) {
            val adaptedMessage = message as ChatEventMessage<T>
            if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptions(scope) { triggerMessage ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerMessage.chat.id.chatId }
        } else null
    ) {
        scenarioReceiver(triggerMessage)
    }
}

suspend fun BehaviourContext.onChannelEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChatEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatStartedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatStarted>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatStarted>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatEndedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatEnded>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatEnded>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVoiceChatParticipantsInvitedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<VoiceChatParticipantsInvited>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<VoiceChatParticipantsInvited>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onMessageAutoDeleteTimerChangedEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<MessageAutoDeleteTimerChanged>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<MessageAutoDeleteTimerChanged>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onCommonEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<CommonEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onGroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupEvent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupEvent>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)

suspend fun BehaviourContext.onChannelChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelChatCreated>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onDeleteChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<DeleteChatPhoto>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onGroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupChatCreated>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onLeftChatMember(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<LeftChatMember>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onNewChatMembers(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatMembers>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onNewChatPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatPhoto>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onNewChatTitle(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatTitle>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onPinnedMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<PinnedMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onProximityAlertTriggered(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ProximityAlertTriggered>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onSupergroupChatCreated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupChatCreated>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
