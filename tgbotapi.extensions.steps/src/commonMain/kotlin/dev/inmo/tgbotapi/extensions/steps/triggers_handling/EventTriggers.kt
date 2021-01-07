package dev.inmo.tgbotapi.extensions.steps.triggers_handling


import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.steps.ScenarioAndTypeReceiver
import dev.inmo.tgbotapi.extensions.steps.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.flow.filter

internal suspend inline fun <reified T : ChatEvent> Scenario.onEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    noinline additionalFilter: (suspend (ChatEventMessage<T>) -> Boolean)? = null,
    noinline scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<T>>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asMessageUpdate() ?.data ?.asChatEventMessage() ?.let { message ->
        if (message.chatEvent is T) {
            val adaptedMessage = message as ChatEventMessage<T>
            if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
        } else {
            null
        }
    }
}.subscribeSafelyWithoutExceptions(scope) { triggerMessage ->
    val (jobToCancel, scenario) = if (includeFilterByChatInSubScenario) {
        val subFilter = FlowsUpdatesFilter()
        val subScenario = copy(flowsUpdatesFilter = subFilter)

        flowsUpdatesFilter.allUpdatesFlow.filter {
            val chat = it.sourceChat() ?: return@filter false
            chat.id.chatId == triggerMessage.chat.id.chatId
        }.subscribeSafelyWithoutExceptions(scope, subFilter.asUpdateReceiver) to subScenario
    } else {
        null to this
    }
    safelyWithoutExceptions { scenario.scenarioReceiver(triggerMessage) }
    jobToCancel ?.cancel()
}

suspend fun Scenario.onChannelEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelEvent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<ChannelEvent>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onChatEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChatEvent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<ChatEvent>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onCommonEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<CommonEvent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<CommonEvent>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onGroupEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupEvent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<GroupEvent>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onSupergroupEvent(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupEvent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<SupergroupEvent>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)

suspend fun Scenario.onChannelChatCreated(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ChannelChatCreated>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<ChannelChatCreated>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onDeleteChatPhoto(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<DeleteChatPhoto>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<DeleteChatPhoto>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onGroupChatCreated(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<GroupChatCreated>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<GroupChatCreated>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onLeftChatMember(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<LeftChatMember>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<LeftChatMember>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onNewChatMembers(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatMembers>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<NewChatMembers>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onNewChatPhoto(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatPhoto>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<NewChatPhoto>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onNewChatTitle(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<NewChatTitle>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<NewChatTitle>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onPinnedMessage(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<PinnedMessage>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<PinnedMessage>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onProximityAlertTriggered(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<ProximityAlertTriggered>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<ProximityAlertTriggered>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onSupergroupChatCreated(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ChatEventMessage<SupergroupChatCreated>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ChatEventMessage<SupergroupChatCreated>>
) = onEvent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
